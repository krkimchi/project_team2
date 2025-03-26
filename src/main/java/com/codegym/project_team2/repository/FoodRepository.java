package com.codegym.project_team2.repository;

import com.codegym.project_team2.dto.DishDto;
import com.codegym.project_team2.model.Food;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository implements IFoodRepository {
    private final String MOST_ORDER_FOOD = "call get_most_ordered_food(?)";
    private final String SEARCH_FOOD = "call search_food(?)";
    private final String SEARCH_FOOD_BY_ID = "select * from dishes where dishes.id = ?";

    @Override
    public List<DishDto> getMostOrderedFoods() {
        List<DishDto> foodList = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            CallableStatement callableStatement = connection.prepareCall(MOST_ORDER_FOOD);
            int MOST_FOOD_SHOW = 6;
            callableStatement.setInt(1, MOST_FOOD_SHOW);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String dishName = resultSet.getString("dish_name");
                double dishPrice = Double.parseDouble(resultSet.getString("dish_price"));
                String dishImg = resultSet.getString("dish_img");
                String dishDesc = resultSet.getString("description");
                String restaurantName = resultSet.getString("restaurant_name");
                int totalOrdered = resultSet.getInt("total_ordered");
                DishDto dishDto = new DishDto(id, dishName, dishPrice, dishImg, dishDesc, restaurantName, totalOrdered);
                foodList.add(dishDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foodList;
    }

    @Override
    public List<DishDto> searchFood(String keyword) {
        List<DishDto> foodList = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            CallableStatement callableStatement = connection.prepareCall(SEARCH_FOOD);
            callableStatement.setString(1, keyword);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String dishName = resultSet.getString("dish_name");
                double dishPrice = Double.parseDouble(resultSet.getString("dish_price"));
                String dishImg = resultSet.getString("dish_img");
                String dishDesc = resultSet.getString("description");
                String restaurantName = resultSet.getString("restaurant_name");
                int totalOrdered = resultSet.getInt("total_ordered");
                DishDto dishDto = new DishDto(id, dishName, dishPrice, dishImg, dishDesc, restaurantName, totalOrdered);
                foodList.add(dishDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foodList;
    }

    @Override
    public Food getFoodById(int id) {
        Food food = null;
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_FOOD_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int dishId = resultSet.getInt("id");
                int restaurantId = resultSet.getInt("restaurant_id");
                String dishName = resultSet.getString("dish_name");
                double dishPrice = Double.parseDouble(resultSet.getString("dish_price"));
                String dishImg = resultSet.getString("dish_img");
                String dishDesc = resultSet.getString("description");
                boolean isAvailable = resultSet.getBoolean("is_available");
                String createdAt = resultSet.getString("created_at");
                food = new Food(dishId, restaurantId, dishName, dishPrice, dishImg, dishDesc, isAvailable, createdAt);
                return food;
            }
        } catch (SQLException e) {
            System.out.println("Error when getting food by id :" + e.getMessage());
            throw new RuntimeException(e);
        }
        return food;
    }
}
