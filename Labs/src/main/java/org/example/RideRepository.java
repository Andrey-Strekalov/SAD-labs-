package org.example;

import java.util.List;

public interface RideRepository {
    void save(Ride ride);
    Ride findById(int rideId);
    List<Ride> findAll();
    List<Ride> findByStatus(RideStatus status);
}