package com.codegym.project_team2.service;

import com.codegym.project_team2.dto.RestaurantDto;
import com.codegym.project_team2.model.Restaurant;

import java.util.List;

public interface IRestaurantService {
    List<RestaurantDto> showAll();

    List<Restaurant> getAllRestaurants();

    RestaurantDto show(int ownerId);

    boolean delete(int ownerId);

    boolean update(int ownerId, Restaurant restaurant);

    boolean create(Restaurant restaurant);

    boolean open(int ownerId);

    boolean close(int ownerId);
}
