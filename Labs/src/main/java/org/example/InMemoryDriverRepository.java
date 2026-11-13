package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryDriverRepository implements DriverRepository {
    private final Map<Integer, Driver> drivers = new HashMap<>();

    @Override
    public void save(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    @Override
    public Driver findById(int id) {
        return drivers.get(id);
    }

    @Override
    public List<Driver> findAll() {
        return new ArrayList<>(drivers.values());
    }

    @Override
    public Optional<Driver> findAvailable() {
        return drivers.values().stream()
                .filter(Driver::isAvailable)
                .findFirst();
    }
}