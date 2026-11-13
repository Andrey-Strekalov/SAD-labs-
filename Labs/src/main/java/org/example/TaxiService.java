package org.example;

import java.time.LocalDateTime;
import java.util.Set;

public class TaxiService {
    private final CarRepository carRepo;
    private final DriverRepository driverRepo;
    private final RideService rideService;
    private final ReportService reportService;
    private final AnalyticsService analyticsService;

    public TaxiService(CarRepository carRepo,
                       DriverRepository driverRepo,
                       RideRepository rideRepo,
                       RideService rideService,
                       ReportService reportService,
                       AnalyticsService analyticsService) {
        this.carRepo = carRepo;
        this.driverRepo = driverRepo;
        this.rideService = rideService;
        this.reportService = reportService;
        this.analyticsService = analyticsService;
    }

    public void addCar(Car car) { carRepo.save(car); }
    public void addDriver(Driver driver) { driverRepo.save(driver); }

    public Car findAvailableCar(CarClass carClass) throws NoAvailableCarException {
        return rideService.findAvailableCar(carClass);
    }
    public Driver findAvailableDriver() throws DriverNotAvailableException {
        return rideService.findAvailableDriver();
    }

    public Ride orderRide(String client, CarClass carClass, Route route,
                          Tariff tariff, LocalDateTime departureTime,
                          PaymentMethod payment)
            throws NoAvailableCarException, DriverNotAvailableException {
        return rideService.orderRide(client, carClass, route, tariff, departureTime, payment);
    }

    public void completeRide(int rideId) { rideService.completeRide(rideId); }
    public boolean payRide(int rideId) { return rideService.payRide(rideId); }

    public void rateRide(int rideId, int value, String comment) throws InvalidRatingException {
        rideService.rateRide(rideId, value, comment);
    }

    public void printAllCars() { reportService.printAllCars(); }
    public void printAllDrivers() { reportService.printAllDrivers(); }
    public void printRideHistory() { reportService.printRideHistory(); }

    public double getTotalRevenue() { return analyticsService.getTotalRevenue(); }
    public Set<String> getClients() { return analyticsService.getUniqueClients(); }
}