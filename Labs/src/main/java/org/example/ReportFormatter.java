package org.example;

import java.util.List;

public interface ReportFormatter {
    String formatCars(List<Car> cars);
    String formatDrivers(List<Driver> drivers);
    String formatRides(List<Ride> rides);
}