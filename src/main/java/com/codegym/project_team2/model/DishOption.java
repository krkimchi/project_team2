package com.codegym.project_team2.model;

public class DishOption {
    private Integer id;
    private String dishName;
    private int dishQuantity;
    private String options;
    public DishOption(int id, String dishName, int dishQuantity, String options) {
        this.id = id;
        this.dishName = dishName;
        this.dishQuantity = dishQuantity;
        this.options = options;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
    public void setOptions(String options) {
        this.options = options;
    }
    public Integer getId() {
        return id;
    }
    public String getDishName() {
        return dishName;
    }
    public String getOptions() {
        return options;
    }
    public int getDishQuantity() {
        return dishQuantity;
    }
    public void setDishQuantity(int dishQuantity) {
        this.dishQuantity = dishQuantity;
    }
}
