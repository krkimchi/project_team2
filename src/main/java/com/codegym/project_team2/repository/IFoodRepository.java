package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Food;

import java.util.List;

public interface IFoodRepository {
    List<Food> getMostOrderedFoods();
    List<Food> searchFood(String keyword);
}
