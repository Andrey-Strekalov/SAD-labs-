package org.example;

public class RatingService {
    public void rateDriver(Driver driver, double value) throws InvalidRatingException {
        driver.addRating(value);
    }
}