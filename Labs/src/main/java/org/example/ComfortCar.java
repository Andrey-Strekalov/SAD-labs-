package org.example;

class ComfortCar extends Car {
    public ComfortCar(String licensePlate, String brand, String color) {
        super(licensePlate, brand, color, CarClass.COMFORT);
    }
    @Override public double getBaseRate() { return 35.0; }
    @Override public String getVehicleType() { return "Комфорт"; }
    @Override public double calculateCost(double distanceKm) {
        return super.calculateCost(distanceKm) + 100;
    }
}
