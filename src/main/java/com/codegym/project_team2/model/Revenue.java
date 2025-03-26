package com.codegym.project_team2.model;

public class Revenue {
    private String restaurantName;
    private int year;
    private int month;
    private double totalRevenue;

    public Revenue(String restaurantName, int year, int month, double totalRevenue) {
        this.restaurantName = restaurantName;
        this.year = year;
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
