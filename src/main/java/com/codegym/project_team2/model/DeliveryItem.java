package com.codegym.project_team2.model;

import java.security.Timestamp;

public class DeliveryItem {
    private int orderId;
    private String restaurantName;
    private String customerName;
    private String dishesWithQuantity;
    private Timestamp pickupTime;
    private Timestamp deliveredTime;
    private String deliveryStatus;

    public DeliveryItem(int orderId, String restaurantName, String customerName, String dishesWithQuantity, java.sql.Timestamp pickkupTime, java.sql.Timestamp deliverdTime, String deliveryStatus) {
        this.orderId = orderId;
        this.restaurantName = restaurantName;
        this.customerName = customerName;
        this.dishesWithQuantity = dishesWithQuantity;
        this.pickupTime = pickupTime;
        this.deliveredTime = deliveredTime;
        this.deliveryStatus = deliveryStatus == null ? "" : deliveryStatus;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDishesWithQuantity() {
        return dishesWithQuantity;
    }
    public void setDishesWithQuantity(String dishesWithQuantity) {
        this.dishesWithQuantity = dishesWithQuantity;
    }

    public Timestamp getPickupTime() { return pickupTime; }
    public void setPickupTime(Timestamp pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Timestamp getDeliveredTime() { return deliveredTime; }
    public void setDeliveredTime(Timestamp deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }
}
