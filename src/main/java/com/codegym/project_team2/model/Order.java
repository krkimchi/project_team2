package com.codegym.project_team2.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private int restaurantId;
    private Integer shipperId; // Có thể null
    private String customerNote;
    private String status;
    private LocalDateTime createdAt;
    private double total;
    private List<CartItem> items;

    public Order() {}

    public Order(Customer customer, List<CartItem> items) {
        this.customer = customer;
        this.items = new ArrayList<>(items);;
        this.restaurantId = determineRestaurantId(items); // Lấy restaurantId từ danh sách món
        this.customerNote = "";
        this.status = "pending";
        this.createdAt = LocalDateTime.now();
        this.total = calculateTotal();
    }

    public Order(int id, Customer customer, int restaurantId, Integer shipperId, String customerNote, String status, LocalDateTime createdAt, List<CartItem> items) {
        this.id = id;
        this.customer = customer;
        this.restaurantId = restaurantId;
        this.shipperId = shipperId;
        this.customerNote = customerNote;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
        this.total = calculateTotal();
    }

    private double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getFood().getPrice() * item.getQuantity();
        }
        return total;
    }

    private int determineRestaurantId(List<CartItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("Cannot determine restaurant ID with an empty cart");
        }
        return items.get(0).getFood().getRestaurantId();
    }

    // Getters và setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // chuyển LocalDateTime thành Date
    public Date getCreatedAtAsDate() {
        if (createdAt == null) {
            return null;
        }
        return Date.from(createdAt.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
    }
}
