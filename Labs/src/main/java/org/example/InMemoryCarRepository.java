package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCarRepository implements CarRepository {
    private final Map<String, Car> cars = new HashMap<>();

    @Override
    public void save(Car car) {
        cars.put(car.getLicensePlate(), car);
    }

    @Override
    public Car findByPlate(String plate) {
        return cars.get(plate);
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public Optional<Car> findAvailable(CarClass carClass) {
        return cars.values().stream()
                .filter(c -> c.getCarClass() == carClass)
                .filter(c -> c.getStatus() == VehicleStatus.AVAILABLE)
                .findFirst();
    }
}