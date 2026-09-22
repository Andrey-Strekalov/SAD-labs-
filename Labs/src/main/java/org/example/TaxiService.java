package org.example;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;


class TaxiService {
    private Map<String, Car> cars = new HashMap<>();
    private Map<Integer, Driver> drivers = new HashMap<>();
    private List<Ride> rides = new ArrayList<>();
    private Set<String> clients = new HashSet<>();

    public void addCar(Car car) { cars.put(car.getLicensePlate(), car); }
    public void addDriver(Driver driver) { drivers.put(driver.getId(), driver); }

    // Поиск свободного авто заданного класса
    public Car findAvailableCar(CarClass carClass) throws NoAvailableCarException {
        return cars.values().stream()
                .filter(c -> c.getCarClass() == carClass)
                .filter(c -> c.getStatus() == VehicleStatus.AVAILABLE)
                .findFirst()
                .orElseThrow(() -> new NoAvailableCarException(
                        "Нет свободных автомобилей класса " + carClass));
    }

    public Driver findAvailableDriver() throws DriverNotAvailableException {
        return drivers.values().stream()
                .filter(Driver::isAvailable)
                .findFirst()
                .orElseThrow(() -> new DriverNotAvailableException("Нет свободных водителей"));
    }

    // Оформление заказа
    public Ride orderRide(String client, CarClass carClass, String route, double distanceKm)
            throws NoAvailableCarException, DriverNotAvailableException {

        Car car = findAvailableCar(carClass);
        Driver driver = findAvailableDriver();

        car.setStatus(VehicleStatus.BUSY);
        Ride ride = new Ride(client, driver, car, route, distanceKm);
        driver.setCurrentRide(ride);
        rides.add(ride);
        clients.add(client);
        return ride;
    }
    // Завершение поездки + оценка водителя
    public void completeRide(int rideId, double rating) throws InvalidRatingException {
        Ride ride = rides.stream()
                .filter(r -> r.getRideId() == rideId)
                .findFirst()
                .orElse(null);
        if (ride == null) {
            System.out.println("Поездка #" + rideId + " не найдена");
            return;
        }
        ride.setStatus(RideStatus.COMPLETED);
        ride.getCar().setStatus(VehicleStatus.AVAILABLE);
        ride.getDriver().addRating(rating);
        ride.getDriver().setCurrentRide(null);
    }

    public void printAllCars() {
        System.out.println("\n=== Автопарк ===");
        cars.values().forEach(System.out::println);
    }
    public void printAllDrivers() {
        System.out.println("\n=== Водители ===");
        drivers.values().forEach(System.out::println);
    }
    public void printRideHistory() {
        System.out.println("\n=== История поездок ===");
        rides.forEach(System.out::println);
    }
    public double getTotalRevenue() {
        return rides.stream()
                .filter(r -> r.getStatus() == RideStatus.COMPLETED)
                .mapToDouble(Ride::getCost)
                .sum();
    }
    public Set<String> getClients() { return Collections.unmodifiableSet(clients); }
}

