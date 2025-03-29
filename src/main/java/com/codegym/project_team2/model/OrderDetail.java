package com.codegym.project_team2.model;

public class OrderDetail {
    private int orderId;
    private int dishId;
    private int dishQuantity;
    private String dishName;

    // Constructor
    public OrderDetail(int orderId, int dishId, int dishQuantity) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.dishQuantity = dishQuantity;
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getDishId() { return dishId; }
    public void setDishId(int dishId) { this.dishId = dishId; }
    public int getDishQuantity() { return dishQuantity; }
    public void setDishQuantity(int dishQuantity) { this.dishQuantity = dishQuantity; }
    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }
}