package org.example;

import java.time.LocalDateTime;

enum RideStatus { ORDERED, IN_PROGRESS, COMPLETED, CANCELLED }

class Ride {
    private static int counter = 1;

    private final int rideId;
    private final String client;
    private final Driver driver;
    private final Car car;
    private final Route route;
    private final Tariff tariff;
    private final LocalDateTime departureTime;
    private final PaymentMethod payment;
    private RideStatus status;
    private Rating rating;                       // ← НОВОЕ (nullable)

    public Ride(String client, Driver driver, Car car, Route route,
                Tariff tariff, LocalDateTime departureTime, PaymentMethod payment) {
        this.rideId = counter++;
        this.client = client;
        this.driver = driver;
        this.car = car;
        this.route = route;
        this.tariff = tariff;
        this.departureTime = departureTime;
        this.payment = payment;
        this.status = RideStatus.ORDERED;
    }

    // ===== Переходы состояния =====
    public void start() {
        if (status != RideStatus.ORDERED)
            throw new IllegalStateException("Нельзя начать поездку в статусе " + status);
        status = RideStatus.IN_PROGRESS;
    }
    public void complete() {
        if (status != RideStatus.ORDERED && status != RideStatus.IN_PROGRESS)
            throw new IllegalStateException("Нельзя завершить поездку в статусе " + status);
        status = RideStatus.COMPLETED;
    }
    public void cancel() {
        if (status == RideStatus.COMPLETED)
            throw new IllegalStateException("Нельзя отменить завершённую поездку");
        status = RideStatus.CANCELLED;
    }

    // ===== Стоимость — через Tariff =====
    public double getCost() {
        return tariff.calculate(route.getDistanceKm(), isNight());
    }

    private boolean isNight() {
        int h = departureTime.getHour();
        return h >= 22 || h < 6;
    }

    // ===== Оплата — через PaymentMethod =====
    public boolean pay() {
        if (status != RideStatus.COMPLETED)
            throw new IllegalStateException(
                    "Оплатить можно только завершённую поездку, статус: " + status);
        return payment.pay(getCost());
    }

    // ===== Оценка — один раз, только после завершения =====
    public void setRating(Rating rating) {
        if (status != RideStatus.COMPLETED)
            throw new IllegalStateException(
                    "Оценить можно только завершённую поездку, статус: " + status);
        if (this.rating != null)
            throw new IllegalStateException("Поездка уже оценена: " + this.rating);
        if (rating == null)
            throw new IllegalArgumentException("Rating не может быть null");
        this.rating = rating;
    }

    // ===== Геттеры =====
    public int getRideId() { return rideId; }
    public String getClient() { return client; }
    public Driver getDriver() { return driver; }
    public Car getCar() { return car; }
    public Route getRoute() { return route; }
    public Tariff getTariff() { return tariff; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public PaymentMethod getPayment() { return payment; }
    public Rating getRating() { return rating; }
    public double getDistanceKm() { return route.getDistanceKm(); }
    public RideStatus getStatus() { return status; }

    @Override
    public String toString() {
        return "Поездка #" + rideId + " [" + status + "] " + client + " -> "
                + route + " (" + route.getDistanceKm() + " км, тариф: " + tariff.getName()
                + (isNight() ? ", НОЧЬ" : "") + ", "
                + String.format("%.2f", getCost()) + " руб., оплата: " + payment
                + (rating != null ? ", оценка: " + rating : ", без оценки")
                + ") Водитель: " + driver.getFullName() + ", Авто: " + car.getLicensePlate();
    }
}