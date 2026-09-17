package org.example;

class EconomyCar extends Car {
    public EconomyCar(String licensePlate, String brand, String color) {
        super(licensePlate, brand, color, CarClass.ECONOMY);
    }

    @Override public double getBaseRate() { return 20.0; }
    @Override public String getVehicleType() { return "Эконом"; }
    @Override public double calculateCost(double distanceKm) {
        return super.calculateCost(distanceKm) + 50; // + посадка
    }
}
