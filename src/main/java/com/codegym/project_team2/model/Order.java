package com.codegym.project_team2.model;

import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private List<CartItem> orderItems;
    private String status;

    public Order(Customer customer, List<CartItem> orderItems) {
        this.customer = customer;
        this.orderItems = orderItems;
        this.status = "pending";  // Mặc định trạng thái là "đang chờ xử lý"
    }

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

    public List<CartItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CartItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
