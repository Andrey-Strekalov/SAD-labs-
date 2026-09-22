package org.example;

class BusinessCar extends Car {
    public BusinessCar(String licensePlate, String brand, String color) {
        super(licensePlate, brand, color, CarClass.BUSINESS);
    }
    @Override public double getBaseRate() { return 60.0; }
    @Override public String getVehicleType() { return "Бизнес"; }
    @Override public double calculateCost(double distanceKm) {
        return super.calculateCost(distanceKm) + 200;
    }
}
