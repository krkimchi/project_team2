package com.codegym.project_team2.service;

import com.codegym.project_team2.model.Dish;
import com.codegym.project_team2.repository.DishRepository;
import com.codegym.project_team2.repository.IDishRepository;

import java.util.List;

public class DishService implements IDishService {

    IDishRepository dishRepository = new DishRepository();

    @Override
    public List<Dish> showByRestaurantId(int restaurantId) {
        return dishRepository.showByRestaurantId(restaurantId);
    }

    @Override
    public boolean add(Dish dish) {
        return dishRepository.add(dish);
    }

    @Override
    public boolean delete(int id) {
        return dishRepository.delete(id);
    }
}
