package com.codegym.project_team2.repository;

public class RatingRepository implements IRatingRepository{
    @Override
    public boolean rateFood(int foodId, int rating) {
        return false;
    }

    @Override
    public boolean rateRestaurant(int restaurantId, int rating) {
        return false;
    }

    @Override
    public boolean rateShipper(int shipperId, int rating) {
        return false;
    }
}
