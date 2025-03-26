package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Restaurant;
import java.sql.SQLException;
import java.util.List;

public interface IRestaurantManagementRepository {
    List<Restaurant> getAllRestaurants() throws SQLException;
    boolean updateRestaurant(Restaurant restaurant) throws SQLException;
    boolean deleteRestaurant(int restaurantId) throws SQLException;
    List<Restaurant> searchRestaurants(String query) throws SQLException;
}
