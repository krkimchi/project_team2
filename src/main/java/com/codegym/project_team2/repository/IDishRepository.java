package com.codegym.project_team2.repository;

import com.codegym.project_team2.dto.RestaurantDto;
import com.codegym.project_team2.model.Dish;

import java.util.List;

public interface IDishRepository {
    List<Dish> showByRestaurantId(int restaurantId);
}
