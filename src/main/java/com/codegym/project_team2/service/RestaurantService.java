package com.codegym.project_team2.service;

import com.codegym.project_team2.model.Restaurant;
import com.codegym.project_team2.repository.IRestaurantRepository;
import com.codegym.project_team2.repository.RestaurantRepository;

import java.util.List;

public class RestaurantService implements IRestaurantService {
    IRestaurantRepository restaurantRepository = new RestaurantRepository();

    @Override
    public List<Restaurant> showAll() {
        return restaurantRepository.showAll();
    }

    @Override
    public Restaurant show(String ownerId) {
        return restaurantRepository.show(ownerId);
    }

    @Override
    public boolean delete(String ownerId) {
        return restaurantRepository.delete(ownerId);
    }

    @Override
    public boolean update(String ownerId, Restaurant restaurant) {
        return restaurantRepository.update(ownerId, restaurant);
    }

    @Override
    public boolean create(Restaurant restaurant) {
        return restaurantRepository.create(restaurant);
    }
}
