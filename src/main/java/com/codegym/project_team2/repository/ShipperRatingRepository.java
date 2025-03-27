package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.ShipperRating;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipperRatingRepository implements IShipperRatingRepository {

    @Override
    public List<ShipperRating> getAllRatings() throws SQLException {
        List<ShipperRating> ratings = new ArrayList<>();
        String query = "select order_id, AVG(rating) as averageRating, group_concat(content SEPARATOR ', ') as content " +
                "from reviews " +
                "where target_type = 'shipper' " +
                "group by order_id";

        try (Connection conn = BaseRepository.getConnectDB()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ratings.add(new ShipperRating(
                        rs.getInt("order_id"),
                        rs.getDouble("averageRating"),
                        rs.getString("content")
                ));
            }
        }

        return ratings;
    }
}
