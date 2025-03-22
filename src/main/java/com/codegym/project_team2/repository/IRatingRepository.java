package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Food;

public interface IRatingRepository {
    boolean rateFood(int foodId, int rating);
    boolean rateRestaurant(int restaurantId, int rating);
    boolean rateShipper(int shipperId, int rating);
}
