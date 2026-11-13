package org.example;

public class Tariff {
    private final String name;
    private final double pricePerKm;
    private final double nightMultiplier;   // например, 1.5 — ночью дороже в 1.5 раза

    public Tariff(String name, double pricePerKm, double nightMultiplier) {
        if (pricePerKm <= 0)
            throw new IllegalArgumentException("pricePerKm должен быть > 0, получено: " + pricePerKm);
        if (nightMultiplier < 1)
            throw new IllegalArgumentException("nightMultiplier должен быть >= 1, получено: " + nightMultiplier);
        this.name = name;
        this.pricePerKm = pricePerKm;
        this.nightMultiplier = nightMultiplier;
    }

    public String getName() { return name; }
    public double getPricePerKm() { return pricePerKm; }

    public double calculate(double distanceKm, boolean isNight) {
        double base = distanceKm * pricePerKm;
        return isNight ? base * nightMultiplier : base;
    }

    @Override
    public String toString() {
        return name + " (" + pricePerKm + " руб/км, ночь ×" + nightMultiplier + ")";
    }
}