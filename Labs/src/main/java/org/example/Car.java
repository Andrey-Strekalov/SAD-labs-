package org.example;

enum CarClass { ECONOMY, COMFORT, BUSINESS}

public abstract class Car extends Vehicle {

    private CarClass carClass;

    public Car(String licensePlate, String brand, String color, CarClass carClass) {
        super(licensePlate, brand, color);
        this.carClass = carClass;
    }

    public CarClass getCarClass() { return carClass; }
    public void setCarClass(CarClass carClass) { this.carClass = carClass; }

    public double calculateCost(double distanceKm) {
        return getBaseRate() * distanceKm;
    }

}
