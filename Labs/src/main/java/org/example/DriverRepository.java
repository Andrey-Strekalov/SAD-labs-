package org.example;

import java.util.List;
import java.util.Optional;

public interface DriverRepository {
    void save(Driver driver);
    Driver findById(int id);
    List<Driver> findAll();
    Optional<Driver> findAvailable();
}