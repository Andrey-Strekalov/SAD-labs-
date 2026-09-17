package org.example;

class Driver extends Person {
    private double rating;
    private int ratingCount;
    private double ratingSum;
    private Ride currentRide;

    public Driver(int id, String fullName) {
        super(id, fullName);
        this.rating = 5.0;
    }

    public double getRating() { return rating; }
    public Ride getCurrentRide() { return currentRide; }
    public void setCurrentRide(Ride currentRide) { this.currentRide = currentRide; }
    public boolean isAvailable() { return currentRide == null; }

    public void addRating(double newRating) throws InvalidRatingException {
        if (newRating < 1 || newRating > 5)
            throw new InvalidRatingException("Оценка должна быть от 1 до 5, получено: " + newRating);
        ratingSum += newRating;
        ratingCount++;
        rating = ratingSum / ratingCount;
    }

    @Override public String getRole() { return "Водитель"; }

    @Override
    public String toString() {
        return getRole() + " #" + getId() + " " + getFullName()
                + " (рейтинг: " + String.format("%.1f", rating) + ")";
    }
}
