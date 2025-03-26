package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Revenue;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RevenueRepository implements IRevenueRepository {


    @Override
    public List<Revenue> getTop20Restaurants() throws SQLException {
        List<Revenue> revenues = new ArrayList<>();
        String query = "select r.restaurant_name, year(p.created_at) as year, month(p.created_at) as month, sum(p.amount) as total_revenue " +
                "from payments p " +
                "join orders o on p.order_id = o.id " +
                "join restaurants r on o.restaurant_id = r.id " +
                "group by r.restaurant_name, year(p.created_at), month(p.created_at) " +
                "order by total_revenue desc limit 20";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String restaurantName = rs.getString("restaurant_name");
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                double totalRevenue = rs.getDouble("total_revenue");

                revenues.add(new Revenue(restaurantName, year, month, totalRevenue));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return revenues;
    }
}
