package com.codegym.project_team2.model;

import java.time.LocalDateTime;

public class Dish {
    private int id;
    private int restaurantId;
    private String dishName;
    private float dishPrice;
    private String dishImg;
    private String description;
    private boolean isAvailable;
    private LocalDateTime createdAt;

    public Dish() {
    }

    public Dish(int id, int restaurantId, String dishName, float dishPrice, String dishImg, String description,
                boolean isAvailable, LocalDateTime createdAt) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishImg = dishImg;
        this.description = description;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
    }

    public Dish (int restaurantId, String dishName, float dishPrice, String dishImg, String description, boolean isAvailable) {
        this.restaurantId = restaurantId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishImg = dishImg;
        this.description = description;
        this.isAvailable = isAvailable;
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

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(float dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishImg() {
        return dishImg;
    }

    public void setDishImg(String dishImg) {
        this.dishImg = dishImg;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
