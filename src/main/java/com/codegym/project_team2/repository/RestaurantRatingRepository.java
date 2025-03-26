package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.RestaurantRating;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRatingRepository implements IRestaurantRatingRepository {

    @Override
    public List<RestaurantRating> getAllRatings() throws SQLException {
        List<RestaurantRating> ratings = new ArrayList<>();
        try (Connection conn = BaseRepository.getConnectDB()) {
            PreparedStatement ps = conn.prepareStatement(
                    "select r.restaurant_name, avg(rev.rating) as averageRating, count(*) as reviewCount " +
                            "from reviews rev join orders o on rev.order_id = o.id " +
                            "join restaurants r on o.restaurant_id = r.id " +
                            "where rev.target_type = 'restaurant' " +
                            "group by r.restaurant_name " +
                            "order by r.restaurant_name, avg(rev.rating) desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ratings.add(new RestaurantRating(
                        rs.getString("restaurant_name"),
                        rs.getDouble("averageRating"),
                        rs.getInt("reviewCount")
                ));
            }
        }
        return ratings;
    }
}
