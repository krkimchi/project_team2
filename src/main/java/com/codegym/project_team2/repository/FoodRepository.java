package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Food;

import java.util.List;

public class FoodRepository implements IFoodRepository{
    @Override
    public List<Food> getMostOrderedFoods() {
        return List.of();
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return List.of();
    }
}
