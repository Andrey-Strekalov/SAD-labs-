package org.example;

import java.util.Set;
import java.util.stream.Collectors;

public class AnalyticsService {
    private final RideRepository rideRepo;

    public AnalyticsService(RideRepository rideRepo) {
        this.rideRepo = rideRepo;
    }

    public double getTotalRevenue() {
        return rideRepo.findByStatus(RideStatus.COMPLETED).stream()
                .mapToDouble(Ride::getCost)
                .sum();
    }

    public Set<String> getUniqueClients() {
        return rideRepo.findAll().stream()
                .map(Ride::getClient)
                .collect(Collectors.toSet());
    }
}