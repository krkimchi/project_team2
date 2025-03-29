package com.codegym.project_team2.service;

import com.codegym.project_team2.dto.DishDto;
import com.codegym.project_team2.model.CartItem;
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

    @Override
    public Food getFoodById(int id) {
        return foodRepository.getFoodById(id);
    }

    @Override
    public List<CartItem> getCartItemsByOrderId(int orderId) {
        return foodRepository.getCartItemsByOrderId(orderId);
    }

    @Override
    public List<Food> getFoodByRestaurantId(int restaurantId) {
        return foodRepository.getFoodByRestaurantId(restaurantId);
    }
}
