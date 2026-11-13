package org.example;

enum VehicleStatus { AVAILABLE, BUSY, MAINTENANCE }

abstract class Vehicle {
    private final String licensePlate;   // ← final: ключ в Map
    private String brand;
    private String color;
    private VehicleStatus status;

    public Vehicle(String licensePlate, String brand, String color) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.color = color;
        this.status = VehicleStatus.AVAILABLE;
    }

    // ===== Инкапсулированные переходы состояния =====
    public void occupy() {
        if (status != VehicleStatus.AVAILABLE)
            throw new IllegalStateException("Машина недоступна: " + status);
        status = VehicleStatus.BUSY;
    }

    public void release() {
        if (status != VehicleStatus.BUSY)
            throw new IllegalStateException("Машина не занята: " + status);
        status = VehicleStatus.AVAILABLE;
    }

    public void sendToMaintenance() {
        if (status == VehicleStatus.BUSY)
            throw new IllegalStateException("Нельзя отправить на ТО занятую машину");
        status = VehicleStatus.MAINTENANCE;
    }

    // ===== Геттеры =====
    public String getLicensePlate() { return licensePlate; }
    public String getBrand() { return brand; }
    public String getColor() { return color; }
    public VehicleStatus getStatus() { return status; }

    // ===== Сеттеры для НЕключевых полей (безопасны) =====
    public void setBrand(String brand) { this.brand = brand; }
    public void setColor(String color) { this.color = color; }

    // ===== Абстрактные методы =====
    public abstract double getBaseRate();
    public abstract String getVehicleType();

    @Override
    public String toString() {
        return getVehicleType() + " [" + licensePlate + "] " + brand
                + " " + color + " (" + status + ")";
    }
}