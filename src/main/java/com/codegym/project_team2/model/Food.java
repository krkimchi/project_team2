package com.codegym.project_team2.model;

public class Food {
    private int id;
    private String name; // Tên món ăn
    private String restaurantName; // Tên nhà hàng
    private double price; // Giá món ăn

    // Constructor, Getter and Setter
    public Food() {
    }

    public Food(int id, String name, String restaurantName, double price) {
        this.id = id;
        this.name = name;
        this.restaurantName = restaurantName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
