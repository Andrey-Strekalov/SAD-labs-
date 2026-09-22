package org.example;

public class TaxiSystemDemo {
    public static void main(String[] args) {
        TaxiService service = new TaxiService();

        // Автомобили разных классов — полиморфизм
        service.addCar(new EconomyCar("А123БВ", "Lada", "Белый"));
        service.addCar(new EconomyCar("Б456ГД", "Kia", "Серый"));
        service.addCar(new ComfortCar("В789ЕЖ", "Toyota", "Черный"));
        service.addCar(new BusinessCar("Г012ЗИ", "Mercedes", "Черный"));

        // Водители (обычные и старший)
        service.addDriver(new Driver(1, "Иванов Иван"));
        service.addDriver(new Driver(2, "Петров Петр"));
        service.addDriver(new SeniorDriver(3, "Сидоров Сидор", 10));

        service.printAllCars();
        service.printAllDrivers();

        try {
            System.out.println("\n=== Оформление заказов ===");
            Ride r1 = service.orderRide("Клиент А", CarClass.ECONOMY,
                    "ул. Ленина 1 -> ул. Пушкина 10", 5.5);
            System.out.println("Создана: " + r1);

            Ride r2 = service.orderRide("Клиент Б", CarClass.COMFORT,
                    "Аэропорт -> Центр", 25.0);
            System.out.println("Создана: " + r2);

            service.completeRide(r1.getRideId(), 5.0);
            System.out.println("Поездка #" + r1.getRideId() + " завершена, оценка 5");

            Ride r3 = service.orderRide("Клиент А", CarClass.BUSINESS,
                    "Вокзал -> Отель", 12.0);
            System.out.println("Создана: " + r3);

        } catch (TaxiException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Проверка исключения — нет свободных машин нужного класса
        try {
            service.orderRide("Клиент В", CarClass.BUSINESS, "Тест", 5.0);
            service.orderRide("Клиент Г", CarClass.BUSINESS, "Тест 2", 5.0);
        } catch (TaxiException e) {
            System.out.println("Ошибка при заказе: " + e.getMessage());
        }

        // Проверка исключения — неверная оценка
        try {
            service.completeRide(2, 10.0);
        } catch (InvalidRatingException e) {
            System.out.println("Ошибка оценки: " + e.getMessage());
        }

        service.printRideHistory();
        System.out.println("\nОбщая выручка: "
                + String.format("%.2f", service.getTotalRevenue()) + " руб.");
        System.out.println("Уникальные клиенты: " + service.getClients());
    }
}
