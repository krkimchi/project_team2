package com.codegym.project_team2.repository;

import com.codegym.project_team2.dto.DishDto;
import com.codegym.project_team2.model.Food;

import java.util.List;

public interface IFoodRepository {
    List<DishDto> getMostOrderedFoods();
    List<DishDto> searchFood(String keyword);
    Food getFoodById(int id);

}
