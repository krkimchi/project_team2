package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.RestaurantRating;

import java.sql.SQLException;
import java.util.List;

public interface IRestaurantRatingRepository {
    List<RestaurantRating> getAllRatings() throws SQLException;
}
