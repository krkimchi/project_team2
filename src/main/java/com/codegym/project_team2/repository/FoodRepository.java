package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.DishDto;
import com.codegym.project_team2.model.Food;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository implements IFoodRepository {
    private final int MOST_FOOD_SHOW = 6;
    private final String MOST_ORDER_FOOD = "call get_most_ordered_food(?)";
    private final String SEARCH_FOOD = "call search_food(?)";

    @Override
    public List<DishDto> getMostOrderedFoods() {
        List<DishDto> foodList = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            CallableStatement callableStatement = connection.prepareCall(MOST_ORDER_FOOD);
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
    public List<DishDto>  searchFood(String keyword) {
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
}
