package org.example;

public class ReportService {
    private final CarRepository carRepo;
    private final DriverRepository driverRepo;
    private final RideRepository rideRepo;
    private final ReportFormatter formatter;

    public ReportService(CarRepository carRepo,
                         DriverRepository driverRepo,
                         RideRepository rideRepo,
                         ReportFormatter formatter) {
        this.carRepo = carRepo;
        this.driverRepo = driverRepo;
        this.rideRepo = rideRepo;
        this.formatter = formatter;
    }

    public void printAllCars() {
        System.out.println(formatter.formatCars(carRepo.findAll()));
    }

    public void printAllDrivers() {
        System.out.println(formatter.formatDrivers(driverRepo.findAll()));
    }

    public void printRideHistory() {
        System.out.println(formatter.formatRides(rideRepo.findAll()));
    }
}