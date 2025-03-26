package com.codegym.project_team2.model;

public class ShipperRevenue {
    private String shipperName;
    private int totalOrders;

    public ShipperRevenue(String shipperName, int totalOrders) {
        this.shipperName = shipperName;
        this.totalOrders = totalOrders;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }
}
