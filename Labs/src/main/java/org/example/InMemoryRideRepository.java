package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRideRepository implements RideRepository {
    private final Map<Integer, Ride> rides = new LinkedHashMap<>();

    @Override
    public void save(Ride ride) {
        rides.put(ride.getRideId(), ride);
    }

    @Override
    public Ride findById(int rideId) {
        return rides.get(rideId);
    }

    @Override
    public List<Ride> findAll() {
        return new ArrayList<>(rides.values());
    }

    @Override
    public List<Ride> findByStatus(RideStatus status) {
        return rides.values().stream()
                .filter(r -> r.getStatus() == status)
                .toList();
    }
}