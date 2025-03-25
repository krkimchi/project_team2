package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.DeliveryItem;
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
                Timestamp pickkupTime = resultSet.getTimestamp("PickkupTime");
                Timestamp deliverdTime = resultSet.getTimestamp("DeliveredTime");
                String deliveryStatus = resultSet.getString("DeliveryStatus");
                DeliveryItem deliveryItem = new DeliveryItem(orderId, restaurantName, customerName, dishesWithQuantity, pickkupTime, deliverdTime, deliveryStatus);
                deliveryItems.add(deliveryItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deliveryItems;
    }
}
