package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.Food;

import java.util.List;

public interface IFoodController {
    List<Food> getMostOrderedFoods();
    List<Food> searchFood(String keyword);
}
