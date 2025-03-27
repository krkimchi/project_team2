package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.RestaurantRevenue;
import com.codegym.project_team2.model.ShipperRevenue;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RevenueRepository implements IRevenueRepository {


    @Override
    public List<RestaurantRevenue> getTop20Restaurants() throws SQLException {
        List<RestaurantRevenue> restaurantRevenues = new ArrayList<>();
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

                restaurantRevenues.add(new RestaurantRevenue(restaurantName, year, month, totalRevenue));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return restaurantRevenues;
    }

    @Override
    public List<ShipperRevenue> getTop20Shippers() throws SQLException {
        List<ShipperRevenue> shipperRevenue = new ArrayList<>();
        String query = "select so.shipper_id, u.username, count(so.order_id) as order_count " +
                "from shipper_orders so " +
                "join users u on so.shipper_id = u.id " +
                "where year(so.pickup_time) = year(current_date) " +
                "and month(so.pickup_time) between 1 and 6 " +
                "and so.status = 'delivered' " +
                "group by so.shipper_id, u.username " +
                "order by order_count desc " +
                "limit 20";


        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String shipperName = rs.getString("username");
                int orderCount = rs.getInt("order_count");
                shipperRevenue.add(new ShipperRevenue(shipperName, orderCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return shipperRevenue;
    }
}
