package com.codegym.project_team2.controller;

public interface IRatingController {
    boolean rateFood(int foodId, int rating);
    boolean rateRestaurant(int restaurantId, int rating);
    boolean rateShipper(int shipperId, int rating);
}
