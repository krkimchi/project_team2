package com.codegym.project_team2.repository;

import com.codegym.project_team2.dto.RestaurantDto;
import com.codegym.project_team2.model.Restaurant;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository implements IRestaurantRepository {
    final String FIND_BY_ID = "SELECT " +
            "   r.id, " +
            "   u.full_name as owner_name, " +
            "   r.restaurant_name, " +
            "   r.restaurant_address, " +
            "   r.restaurant_phone, " +
            "   r.restaurant_logo, " +
            "   r.is_open, " +
            "   r.created_at " +
            "FROM " +
            "   restaurants r JOIN users u " +
            "   ON r.owner_id = u.id " +
            "where r.owner_id = ? ";

    final String OPEN = "UPDATE restaurants SET is_open = 1 WHERE id = ?";
    final String CLOSE = "UPDATE restaurants SET is_open = 0 WHERE id = ?";
    private final String SELECT_ALL_RESTAURANTS = "SELECT * FROM restaurants WHERE is_open = 1";

    @Override
    public List<RestaurantDto> showAll() {
        List<RestaurantDto> restaurantList = new ArrayList<>();
        return restaurantList;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        Connection connection = BaseRepository.getConnectDB();
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RESTAURANTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int ownerId = resultSet.getInt("owner_id");
                String restaurantName = resultSet.getString("restaurant_name");
                String restaurantAddress = resultSet.getString("restaurant_address");
                String restaurantPhone = resultSet.getString("restaurant_phone");
                String restaurantLogo = resultSet.getString("restaurant_logo");
                boolean isOpen = resultSet.getBoolean("is_open");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                Restaurant restaurant = new Restaurant(id, ownerId, restaurantName, restaurantAddress, restaurantPhone, restaurantLogo, isOpen, createdAt);
                restaurantList.add(restaurant);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return restaurantList;
    }

    @Override
    public RestaurantDto show(int ownerId) {
        try {
            Connection connection = BaseRepository.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, ownerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapToRestaurant(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int ownerId) {
        return false;
    }

    @Override
    public boolean update(int ownerId, Restaurant restaurant) {
        return false;
    }

    @Override
    public boolean create(Restaurant restaurant) {
        return false;
    }

    @Override
    public boolean open(int ownerId) {
        try {
            Connection connection = BaseRepository.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(OPEN);
            preparedStatement.setInt(1, ownerId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean close(int ownerId) {
        try {
            Connection connection = BaseRepository.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(CLOSE);
            preparedStatement.setInt(1, ownerId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private RestaurantDto mapToRestaurant(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String ownerName = resultSet.getString("owner_name");
        String restaurantName = resultSet.getString("restaurant_name");
        String restaurantAddress = resultSet.getString("restaurant_address");
        String restaurantPhone = resultSet.getString("restaurant_phone");
        String restaurantLogo = resultSet.getString("restaurant_logo");
        boolean isOpen = resultSet.getBoolean("is_open");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();

        return new RestaurantDto(id, ownerName, restaurantName, restaurantAddress, restaurantPhone, restaurantLogo, isOpen, createdAt);
    }
}