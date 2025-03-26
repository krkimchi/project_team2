package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Restaurant;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantManagementRepository implements IRestaurantManagementRepository {

    @Override
    public List<Restaurant> getAllRestaurants() throws SQLException {
        String query = "SELECT * FROM restaurants";
        List<Restaurant> restaurantList = new ArrayList<>();

        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(resultSet.getInt("id"));
                restaurant.setRestaurantName(resultSet.getString("restaurant_name"));
                restaurant.setRestaurantAddress(resultSet.getString("restaurant_address"));
                restaurant.setRestaurantPhone(resultSet.getString("restaurant_phone"));
                restaurant.setRestaurantLogo(resultSet.getString("restaurant_logo"));
                restaurant.setOpen(resultSet.getBoolean("is_open"));
                restaurant.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                restaurantList.add(restaurant);
            }
        }
        return restaurantList;
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) throws SQLException {
        String query = "UPDATE restaurants SET restaurant_name = ?, restaurant_address = ?, restaurant_phone = ? WHERE id = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, restaurant.getRestaurantName());
            preparedStatement.setString(2, restaurant.getRestaurantAddress());
            preparedStatement.setString(3, restaurant.getRestaurantPhone());
            preparedStatement.setInt(4, restaurant.getId());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        }
    }

    @Override
    public boolean deleteRestaurant(int restaurantId) throws SQLException {
        String query = "DELETE FROM restaurants WHERE id = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, restaurantId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        }
    }

    @Override
    public List<Restaurant> searchRestaurants(String query) throws SQLException {
        List<Restaurant> restaurantList = new ArrayList<>();

        // Truy vấn tìm kiếm theo Restaurant Name hoặc Restaurant ID
        String sql = "SELECT * FROM restaurants WHERE restaurant_name LIKE ? OR id LIKE ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            String searchPattern = "%" + query + "%";
            preparedStatement.setString(1, searchPattern);
            preparedStatement.setString(2, "%" + query + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(resultSet.getInt("id"));
                restaurant.setRestaurantName(resultSet.getString("restaurant_name"));
                restaurant.setRestaurantAddress(resultSet.getString("restaurant_address"));
                restaurant.setRestaurantPhone(resultSet.getString("restaurant_phone"));
                restaurant.setRestaurantLogo(resultSet.getString("restaurant_logo"));
                restaurant.setOpen(resultSet.getBoolean("is_open"));
                restaurant.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                restaurantList.add(restaurant);
            }
        }

        return restaurantList;
    }
}
