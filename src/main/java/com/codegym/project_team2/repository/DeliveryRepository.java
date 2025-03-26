package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.DeliveryItem;
import com.codegym.project_team2.model.DishOption;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepository implements IDeliveryRepository {
    @Override
    public List<DeliveryItem> getDeliveryItems(int id) {
        String query = "call GetShipperDeliveryList(?)";
        List<DeliveryItem> deliveryItems = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderId");
                String restaurantName = resultSet.getString("RestaurantName");
                String customerName = resultSet.getString("CustomerName");
                String dishesWithQuantity = resultSet.getString("DishesWithQuantity");
                Timestamp pickkupTime = resultSet.getTimestamp("PickupTime");
                Timestamp deliverdTime = resultSet.getTimestamp("DeliveredTime");
                String deliveryStatus = resultSet.getString("DeliveryStatus");
                double deliveryPrice = resultSet.getDouble("TotalPrice");
                DeliveryItem deliveryItem = new DeliveryItem(orderId, restaurantName, customerName, dishesWithQuantity, pickkupTime, deliverdTime, deliveryStatus, deliveryPrice);
                deliveryItems.add(deliveryItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deliveryItems;
    }

    @Override
    public DeliveryItem getDeliveryItemById(int id) {
        String query = "call GetOrderDetails(?)";
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                int orderId = resultSet.getInt("OrderId");
                String restaurantName = resultSet.getString("RestaurantName");
                String customerName = resultSet.getString("CustomerName");
                String dishesWithQuantity = resultSet.getString("DishesWithQuantity");
                Timestamp pickkupTime = resultSet.getTimestamp("PickupTime");
                Timestamp deliverdTime = resultSet.getTimestamp("DeliveredTime");
                String deliveryStatus = resultSet.getString("DeliveryStatus");
                double deliveryPrice = resultSet.getDouble("TotalPrice");
                DeliveryItem deliveryItem = new DeliveryItem(orderId, restaurantName, customerName, dishesWithQuantity, pickkupTime, deliverdTime, deliveryStatus, deliveryPrice);
                return deliveryItem;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<DishOption> getDishesWithOption(int id) {
        String query = "call GetOrderDishesWithOptions(?)";
        List<DishOption> dishesOptions = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int dishId = resultSet.getInt("DishID");
                String dishName = resultSet.getString("DishName");
                int dishQuantity = resultSet.getInt("Quantity");
                String options = resultSet.getString("Options");
                DishOption dishOption = new DishOption(dishId, dishName, dishQuantity, options);
                dishesOptions.add(dishOption);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dishesOptions;
    }
}
