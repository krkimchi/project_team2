package com.codegym.project_team2.service;

import com.codegym.project_team2.model.Dish;

import java.util.List;

public interface IDishService {
    List<Dish> showByRestaurantId(int restaurantId);

    boolean add(Dish dish);

    boolean delete(int id);
}
