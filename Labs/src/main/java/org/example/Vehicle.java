package org.example;

enum VehicleStatus { AVAILABLE, BUSY, MAINTENANCE }


// Уровень 1
abstract class Vehicle {
    private String licensePlate;
    private String brand;
    private String color;
    private VehicleStatus status;

    public Vehicle(String licensePlate, String brand, String color) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.color = color;
        this.status = VehicleStatus.AVAILABLE;
    }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public VehicleStatus getStatus() { return status; }
    public void setStatus(VehicleStatus status) { this.status = status; }

    // Абстрактные методы — полиморфизм
    public abstract double getBaseRate();
    public abstract String getVehicleType();

    @Override
    public String toString() {
        return getVehicleType() + " [" + licensePlate + "] " + brand
                + " " + color + " (" + status + ")";
    }
}