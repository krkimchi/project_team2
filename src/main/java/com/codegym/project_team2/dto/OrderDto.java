package com.codegym.project_team2.dto;

import com.codegym.project_team2.model.OrderDetail;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private int id;
    private int customerId;
    private int restaurantId;
    private Integer shipperId; // Có thể null nếu chưa có shipper
    private String customerNote;
    private String status; // pending, confirmed, preparing, ready, delivering, completed, cancelled
    private LocalDateTime createdAt;
    private String restaurantName;
    private String shipperName;
    private Double totalPrice;
    private List<OrderDetail> orderDetails;

    public OrderDto(int id, int customerId, int restaurantId, Integer shipperId, String customerNote, String status, LocalDateTime createdAt, String restaurantName, String shipperName, Double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.shipperId = shipperId;
        this.customerNote = customerNote;
        this.status = status;
        this.createdAt = createdAt;
        this.restaurantName = restaurantName;
        this.shipperName = shipperName;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedAtAsDate() {
        if (createdAt == null) {
            return null;
        }
        return Date.from(createdAt.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
    }
}
