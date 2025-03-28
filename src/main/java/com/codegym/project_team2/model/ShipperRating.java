package com.codegym.project_team2.model;

public class ShipperRating {
    private int orderId;
    private double averageRating;
    private String content;

    public ShipperRating(int orderId, double averageRating, String content) {
        this.orderId = orderId;
        this.averageRating = averageRating;
        this.content = content;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getContent() {
        return content;
    }
}
