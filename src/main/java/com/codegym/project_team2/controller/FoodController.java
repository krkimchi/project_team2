package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.Food;
import com.codegym.project_team2.repository.FoodRepository;
import com.codegym.project_team2.repository.IFoodRepository;

import java.util.List;

public class FoodController implements IFoodController{
    private IFoodRepository foodRepository = new FoodRepository();

    @Override
    public List<Food> getMostOrderedFoods() {
        return List.of();
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return List.of();
    }
}
