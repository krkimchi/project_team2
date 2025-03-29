package com.codegym.project_team2.model;

public class Shipper {
    private int id;
    private String name;
    private int ordersCount;
    public Shipper(int id, String name, int ordersCount) {
        this.id = id;
        this.name = name;
        this.ordersCount = ordersCount;
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
    public int getOrdersCount() {
        return ordersCount;
    }
    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }
}
