package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Dish;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DishRepository implements IDishRepository {
    final String FIND_BY_RESTAURANT_ID = "select * from dishes where restaurant_id = ?";
    final String INSERT_INTO = "insert into dishes(restaurant_id, dish_name, dish_price, dish_img, description, is_available) values(?, ?, ?, ?, ?, ?)";

    @Override
    public List<Dish> showByRestaurantId(int restaurantId) {
        List<Dish> dishList = new ArrayList<>();
        try {
            Connection connection = BaseRepository.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_RESTAURANT_ID);
            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dishList.add(mapToDish(resultSet));
            }
            return dishList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Dish dish) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            preparedStatement.setInt(1, dish.getRestaurantId());
            preparedStatement.setString(2, dish.getDishName());
            preparedStatement.setFloat(3, dish.getDishPrice());
            preparedStatement.setString(4, dish.getDishImg());
            preparedStatement.setString(5, dish.getDescription());
            preparedStatement.setBoolean(6, dish.isAvailable());
            preparedStatement.executeUpdate();
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private Dish mapToDish(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int restaurantId = resultSet.getInt("restaurant_id");
        String dishName = resultSet.getString("dish_name");
        float dishPrice = resultSet.getFloat("dish_price");
        String dishImg = resultSet.getString("dish_img");
        String description = resultSet.getString("description");
        boolean isAvailable = resultSet.getBoolean("is_available");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();

        return new Dish(id, restaurantId, dishName, dishPrice, dishImg, description, isAvailable, createdAt);
    }
}
