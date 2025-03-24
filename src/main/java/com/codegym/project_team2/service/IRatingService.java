package com.codegym.project_team2.service;

public interface IRatingService {
    boolean rateFood(int foodId, int rating);
    boolean rateRestaurant(int restaurantId, int rating);
    boolean rateShipper(int shipperId, int rating);
}
