package com.codegym.project_team2.service;

import com.codegym.project_team2.model.DishDto;
import com.codegym.project_team2.model.Food;
import com.codegym.project_team2.repository.FoodRepository;
import com.codegym.project_team2.repository.IFoodRepository;

import java.util.List;

public class FoodService implements IFoodService {
    private IFoodRepository foodRepository = new FoodRepository();


    @Override
    public List<DishDto> getMostOrderedFoods() {
        return foodRepository.getMostOrderedFoods();
    }

    @Override
    public List<DishDto> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }
}
