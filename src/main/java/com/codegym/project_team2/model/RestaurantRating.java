package com.codegym.project_team2.model;

public class RestaurantRating {
    private String restaurantName;
    private double averageRating;
    private String content;

    public RestaurantRating(String restaurantName, double averageRating, String content) {
        this.restaurantName = restaurantName;
        this.averageRating = averageRating;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
