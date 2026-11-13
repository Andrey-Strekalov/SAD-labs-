package org.example;

import java.time.LocalDateTime;

public class TaxiSystemDemo {
    public static void main(String[] args) {
        // ===== 1. Сборка инфраструктуры =====
        CarRepository carRepo = new InMemoryCarRepository();
        DriverRepository driverRepo = new InMemoryDriverRepository();
        RideRepository rideRepo = new InMemoryRideRepository();

        // ===== 2. Сборка сервисов =====
        RatingService ratingService = new RatingService();
        RideService rideService = new RideService(carRepo, driverRepo, rideRepo, ratingService);
        AnalyticsService analyticsService = new AnalyticsService(rideRepo);

        // ===== 3. Отчётность — два формата =====
        ReportService textReportService = new ReportService(
                carRepo, driverRepo, rideRepo, new TextReportFormatter());
        ReportService jsonReportService = new ReportService(
                carRepo, driverRepo, rideRepo, new JsonReportFormatter());

        // ===== 4. Фасад =====
        TaxiService service = new TaxiService(
                carRepo, driverRepo, rideRepo,
                rideService, textReportService, analyticsService);

        // ===== 5. Автопарк =====
        service.addCar(new EconomyCar("А123БВ", "Lada", "Белый"));
        service.addCar(new EconomyCar("Б456ГД", "Kia", "Серый"));
        service.addCar(new ComfortCar("В789ЕЖ", "Toyota", "Черный"));
        service.addCar(new BusinessCar("Г012ЗИ", "Mercedes", "Черный"));

        service.addDriver(new Driver(1, "Иванов Иван"));
        service.addDriver(new Driver(2, "Петров Петр"));
        service.addDriver(new SeniorDriver(3, "Сидоров Сидор", 10));

        service.printAllCars();
        service.printAllDrivers();

        // ===== 6. Тарифы =====
        Tariff economyTariff  = new Tariff("Эконом",  20.0, 1.5);
        Tariff comfortTariff  = new Tariff("Комфорт", 35.0, 1.5);
        Tariff businessTariff = new Tariff("Бизнес",  60.0, 2.0);

        // ===== 7. Способы оплаты =====
        PaymentMethod cash = new CashPayment();
        PaymentMethod card = new CardPayment("1234567812345678");

        // ===== 8. Заказы =====
        Ride r1 = null, r2 = null, r3 = null;

        try {
            System.out.println("\n=== Оформление заказов ===");

            r1 = service.orderRide("Клиент А", CarClass.ECONOMY,
                    new Route("ул. Ленина 1", "ул. Пушкина 10", 5.5),
                    economyTariff,
                    LocalDateTime.of(2025, 1, 15, 14, 0),
                    cash);
            System.out.println("Создана: " + r1);

            r2 = service.orderRide("Клиент Б", CarClass.COMFORT,
                    new Route("Аэропорт", "Центр", 25.0),
                    comfortTariff,
                    LocalDateTime.of(2025, 1, 15, 23, 0),
                    card);
            System.out.println("Создана: " + r2);

            // r1: завершение → оплата → оценка с комментарием
            service.completeRide(r1.getRideId());
            System.out.println("Поездка #" + r1.getRideId() + " завершена");
            service.payRide(r1.getRideId());
            System.out.println("Поездка #" + r1.getRideId() + " оплачена");
            service.rateRide(r1.getRideId(), 5, "Отличная поездка");
            System.out.println("Поездка #" + r1.getRideId() + " оценена");

            // r2: завершение → оплата → оценка без комментария
            service.completeRide(r2.getRideId());
            System.out.println("Поездка #" + r2.getRideId() + " завершена");
            service.payRide(r2.getRideId());
            service.rateRide(r2.getRideId(), 4, "");
            System.out.println("Поездка #" + r2.getRideId() + " оценена");

            // r3: бизнес, ночь, карта (пока не завершена)
            r3 = service.orderRide("Клиент А", CarClass.BUSINESS,
                    new Route("Вокзал", "Отель", 12.0),
                    businessTariff,
                    LocalDateTime.of(2025, 1, 16, 3, 0),
                    card);
            System.out.println("Создана: " + r3);

        } catch (TaxiException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // ===== 9. Проверка исключений =====
        // Нет свободных BUSINESS-машин
        try {
            service.orderRide("Клиент В", CarClass.BUSINESS,
                    new Route("A", "B", 5.0), businessTariff,
                    LocalDateTime.of(2025, 1, 16, 10, 0),
                    new CashPayment());
            service.orderRide("Клиент Г", CarClass.BUSINESS,
                    new Route("C", "D", 5.0), businessTariff,
                    LocalDateTime.of(2025, 1, 16, 10, 0),
                    new CashPayment());
        } catch (TaxiException e) {
            System.out.println("Ошибка при заказе: " + e.getMessage());
        }

        // Повторная оценка r1 → IllegalStateException
        if (r1 != null) {
            try {
                service.rateRide(r1.getRideId(), 5, "Повторно");
            } catch (IllegalStateException | InvalidRatingException e) {
                System.out.println("Ошибка оценки (повторно): " + e.getMessage());
            }
        }

        // Оценка НЕЗАВЕРШЁННОЙ поездки r3 → IllegalStateException
        if (r3 != null) {
            try {
                service.rateRide(r3.getRideId(), 5, "Ещё не завершена");
            } catch (IllegalStateException | InvalidRatingException e) {
                System.out.println("Ошибка оценки (не завершена): " + e.getMessage());
            }
        }

        // Невалидная оценка r2 (6) → IllegalArgumentException из Rating
        if (r2 != null) {
            try {
                // переоценить нельзя, но проверим валидацию: возьмём отдельный Rating
                new Rating(6, ">5", java.time.LocalDate.now());
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка валидации Rating: " + e.getMessage());
            }
        }

        // ===== 10. Отчёты =====
        System.out.println("\n\n########## ТЕКСТОВЫЙ ФОРМАТ ##########");
        textReportService.printAllCars();
        textReportService.printAllDrivers();
        textReportService.printRideHistory();

        System.out.println("\n\n########## JSON-ФОРМАТ ##########");
        jsonReportService.printAllCars();
        jsonReportService.printAllDrivers();
        jsonReportService.printRideHistory();

        // ===== 11. Аналитика =====
        System.out.println("\n=== Аналитика ===");
        System.out.println("Общая выручка: "
                + String.format("%.2f", analyticsService.getTotalRevenue()) + " руб.");
        System.out.println("Уникальные клиенты: " + analyticsService.getUniqueClients());
    }
}