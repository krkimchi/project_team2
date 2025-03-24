package com.codegym.project_team2.model;

public class Food {
    private int id;
    private int restaurantId;
    private String name;
    private double price;
    private String foodImg;
    private String description;
    boolean isAvailable;
    String createdAt;
    private String restaurantName;

    public Food() {
    }

    public Food(boolean isAvailable, int id, int restaurantId, String name, double price, String foodImg, String description, String createdAt, String restaurantName) {
        this.isAvailable = isAvailable;
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.foodImg = foodImg;
        this.description = description;
        this.createdAt = createdAt;
        this.restaurantName = restaurantName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
