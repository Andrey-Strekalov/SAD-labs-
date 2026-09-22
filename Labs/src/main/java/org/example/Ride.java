package org.example;

enum RideStatus { ORDERED, IN_PROGRESS, COMPLETED, CANCELLED }

class Ride {
    private static int counter = 1;
    private int rideId;
    private String client;
    private Driver driver;
    private Car car;
    private String route;
    private double distanceKm;
    private double cost;
    private RideStatus status;

    public Ride(String client, Driver driver, Car car, String route, double distanceKm) {
        this.rideId = counter++;
        this.client = client;
        this.driver = driver;
        this.car = car;
        this.route = route;
        this.distanceKm = distanceKm;
        this.cost = car.calculateCost(distanceKm); // полиморфный вызов
        this.status = RideStatus.ORDERED;
    }

    public int getRideId() { return rideId; }
    public String getClient() { return client; }
    public Driver getDriver() { return driver; }
    public Car getCar() { return car; }
    public String getRoute() { return route; }
    public double getDistanceKm() { return distanceKm; }
    public double getCost() { return cost; }
    public RideStatus getStatus() { return status; }
    public void setStatus(RideStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Поездка #" + rideId + " [" + status + "] " + client + " -> "
                + route + " (" + distanceKm + " км, "
                + String.format("%.2f", cost) + " руб.) Водитель: "
                + driver.getFullName() + ", Авто: " + car.getLicensePlate();
    }
}
