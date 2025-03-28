package com.codegym.project_team2.service;

import com.codegym.project_team2.dto.RestaurantDto;
import com.codegym.project_team2.model.Restaurant;
import com.codegym.project_team2.repository.IRestaurantRepository;
import com.codegym.project_team2.repository.RestaurantRepository;

import java.util.List;

public class RestaurantService implements IRestaurantService {
    IRestaurantRepository restaurantRepository = new RestaurantRepository();

    @Override
    public List<RestaurantDto> showAll() {
        return restaurantRepository.showAll();
    }

    @Override
    public RestaurantDto show(int ownerId) {
        return restaurantRepository.show(ownerId);
    }

    @Override
    public boolean delete(int ownerId) {
        return restaurantRepository.delete(ownerId);
    }

    @Override
    public boolean update(int ownerId, Restaurant restaurant) {
        return restaurantRepository.update(ownerId, restaurant);
    }

    @Override
    public boolean create(Restaurant restaurant) {
        return restaurantRepository.create(restaurant);
    }

    @Override
    public boolean open(int ownerId) {
        return restaurantRepository.open(ownerId);
    }

    @Override
    public boolean close(int ownerId) {
        return restaurantRepository.close(ownerId);
    }
}
