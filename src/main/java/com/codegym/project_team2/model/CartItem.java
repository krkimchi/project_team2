package com.codegym.project_team2.model;

public class CartItem {
    private Food food;  // Món ăn
    private int quantity;  // Số lượng

    public CartItem(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public int getDishId() {
        return food.getId();
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

    // Tính giá trị tổng cho món ăn trong giỏ hàng
    public double getTotalPrice() {
        return food.getPrice() * quantity;
    }
}
