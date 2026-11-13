package org.example;

public class Route {
    private final String origin;
    private final String destination;
    private final double distanceKm;

    public Route(String origin, String destination, double distanceKm) {
        if (distanceKm <= 0)
            throw new IllegalArgumentException("Дистанция должна быть > 0, получено: " + distanceKm);
        this.origin = origin;
        this.destination = destination;
        this.distanceKm = distanceKm;
    }

    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public double getDistanceKm() { return distanceKm; }

    @Override
    public String toString() {
        return origin + " -> " + destination;
    }
}