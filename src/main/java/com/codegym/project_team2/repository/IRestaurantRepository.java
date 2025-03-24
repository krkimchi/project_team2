package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Restaurant;

import java.util.List;

public interface IRestaurantRepository {
    List<Restaurant> showAll();

    Restaurant show(String ownerId);

    boolean delete(String ownerId);

    boolean update(String ownerId, Restaurant restaurant);

    boolean create(Restaurant restaurant);
}
