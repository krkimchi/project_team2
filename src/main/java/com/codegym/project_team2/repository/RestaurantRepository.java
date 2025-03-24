package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository implements IRestaurantRepository {
    @Override
    public List<Restaurant> showAll() {
        List<Restaurant> restaurantList = new ArrayList<>();
        return restaurantList;
    }

    @Override
    public Restaurant show(String ownerId) {
        return null;
    }

    @Override
    public boolean delete(String ownerId) {
        return false;
    }

    @Override
    public boolean update(String ownerId, Restaurant restaurant) {
        return false;
    }

    @Override
    public boolean create(Restaurant restaurant) {
        return false;
    }
}
