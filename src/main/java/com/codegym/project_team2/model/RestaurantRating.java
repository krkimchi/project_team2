package com.codegym.project_team2.model;

public class RestaurantRating {
    private String restaurantName;
    private double averageRating;
    private int reviewCount;

    public RestaurantRating(String restaurantName, double averageRating, int reviewCount) {
        this.restaurantName = restaurantName;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
}
