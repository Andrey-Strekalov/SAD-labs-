package org.example;

import java.util.List;

public class TextReportFormatter implements ReportFormatter {

    @Override
    public String formatCars(List<Car> cars) {
        StringBuilder sb = new StringBuilder("\n=== Автопарк ===\n");
        cars.forEach(c -> sb.append(c).append("\n"));
        return sb.toString();
    }

    @Override
    public String formatDrivers(List<Driver> drivers) {
        StringBuilder sb = new StringBuilder("\n=== Водители ===\n");
        drivers.forEach(d -> sb.append(d).append("\n"));
        return sb.toString();
    }

    @Override
    public String formatRides(List<Ride> rides) {
        StringBuilder sb = new StringBuilder("\n=== История поездок ===\n");
        rides.forEach(r -> sb.append(r).append("\n"));
        return sb.toString();
    }
}