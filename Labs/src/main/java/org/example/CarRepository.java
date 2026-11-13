package org.example;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    void save(Car car);
    Car findByPlate(String plate);
    List<Car> findAll();
    Optional<Car> findAvailable(CarClass carClass);
}