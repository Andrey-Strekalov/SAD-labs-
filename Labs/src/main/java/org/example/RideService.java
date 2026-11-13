package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RideService {
    private final CarRepository carRepo;
    private final DriverRepository driverRepo;
    private final RideRepository rideRepo;
    private final RatingService ratingService;

    public RideService(CarRepository carRepo,
                       DriverRepository driverRepo,
                       RideRepository rideRepo,
                       RatingService ratingService) {
        this.carRepo = carRepo;
        this.driverRepo = driverRepo;
        this.rideRepo = rideRepo;
        this.ratingService = ratingService;
    }

    public Car findAvailableCar(CarClass carClass) throws NoAvailableCarException {
        return carRepo.findAvailable(carClass)
                .orElseThrow(() -> new NoAvailableCarException(
                        "Нет свободных автомобилей класса " + carClass));
    }

    public Driver findAvailableDriver() throws DriverNotAvailableException {
        return driverRepo.findAvailable()
                .orElseThrow(() -> new DriverNotAvailableException("Нет свободных водителей"));
    }

    public Ride orderRide(String client, CarClass carClass, Route route,
                          Tariff tariff, LocalDateTime departureTime,
                          PaymentMethod payment)
            throws NoAvailableCarException, DriverNotAvailableException {
        Car car = findAvailableCar(carClass);
        Driver driver = findAvailableDriver();

        car.occupy();
        Ride ride = new Ride(client, driver, car, route, tariff, departureTime, payment);
        driver.assignRide(ride);
        rideRepo.save(ride);
        return ride;
    }

    public void completeRide(int rideId) {
        Ride ride = rideRepo.findById(rideId);
        if (ride == null) {
            System.out.println("Поездка #" + rideId + " не найдена");
            return;
        }
        ride.complete();
        ride.getCar().release();
        ride.getDriver().free();
    }

    public boolean payRide(int rideId) {
        Ride ride = rideRepo.findById(rideId);
        if (ride == null) {
            throw new IllegalArgumentException("Поездка #" + rideId + " не найдена");
        }
        return ride.pay();
    }

    /**
     * Оценить поездку: создаёт Rating, привязывает к Ride,
     * и обновляет средний рейтинг водителя.
     */
    public void rateRide(int rideId, int value, String comment)
            throws InvalidRatingException {
        Ride ride = rideRepo.findById(rideId);
        if (ride == null) {
            throw new IllegalArgumentException("Поездка #" + rideId + " не найдена");
        }
        Rating rating = new Rating(value, comment, LocalDate.now());
        ride.setRating(rating);                              // проверки статуса и повторной оценки
        ratingService.rateDriver(ride.getDriver(), value);   // обновляет агрегат Driver.rating
    }
}