package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.DeliveryItem;
import com.codegym.project_team2.model.DishOption;
import com.codegym.project_team2.model.Shipper;
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
                String deliveryStatus = resultSet.getString("OrderStatus");
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
                String deliveryStatus = resultSet.getString("OrderStatus");
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

    @Override
    public void changeDeliveryStatus(int id, String status) {
        String query = "UPDATE `food_ordering_system`.`orders` SET `status` = ? WHERE (`id` = ?);";
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int showNumbersOfOrdersHistory(int id) {
        String query = "call CountShipperOrders(?)";
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int numberofOrders = resultSet.getInt("NumberOfOrders");
                return numberofOrders;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int shippersOrdersCount() {
        String query = "call GetShipperOrderCounts()";
        List<Shipper> shippers = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int shipperId = resultSet.getInt("ShipperID");
                String shipperName = resultSet.getString("ShipperName");
                int orderCount = resultSet.getInt("CurrentOrderCount");
                shippers.add(new Shipper(shipperId, shipperName, orderCount));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Shipper shipperWithLeastOrders = shippers.get(0);
        return 11;
    }
}
