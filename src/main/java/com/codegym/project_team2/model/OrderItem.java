package com.codegym.project_team2.model;

public class OrderItem {
    private int id;
    private Food food;
    private int quantity;

    public OrderItem() {}

    public OrderItem(int id, Food food, int quantity) {
        this.id = id;
        this.food = food;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
