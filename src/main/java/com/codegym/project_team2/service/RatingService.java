package com.codegym.project_team2.service;

import com.codegym.project_team2.repository.IRatingRepository;
import com.codegym.project_team2.repository.RatingRepository;

public class RatingService implements IRatingService {
    private IRatingRepository ratingRepository = new RatingRepository();

    @Override
    public boolean rateFood(int foodId, int rating) {
        return ratingRepository.rateFood(foodId, rating);
    }

    @Override
    public boolean rateRestaurant(int restaurantId, int rating) {
        return ratingRepository.rateRestaurant(restaurantId, rating);
    }

    @Override
    public boolean rateShipper(int shipperId, int rating) {
        return ratingRepository.rateShipper(shipperId, rating);
    }
}
