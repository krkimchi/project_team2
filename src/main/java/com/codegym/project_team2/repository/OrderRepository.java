package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.CartItem;
import com.codegym.project_team2.model.Food;
import com.codegym.project_team2.model.Order;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final String ADD_ORDER = "insert into orders(customer_id, restaurant_id, shipper_id, customer_note, status, created_at) values(?, ?, ?, ?, ?)";
    private final String ADD_ODER_DETAIL = "insert into order_detail(order_id, dish_id, dish_quantity) values(?, ?, ?)";
    private final String SELECT_ORDER_BY_ID = "select * from orders o where o.id = ?";


    @Override
    public boolean save(Order order) {
        try {
            Connection connection = BaseRepository.getConnectDB();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setInt(2, order.getRestaurantId());
            preparedStatement.setInt(3, order.getShipperId());
            preparedStatement.setString(4, order.getCustomerNote());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setObject(6, order.getCreatedAt());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int orderId = resultSet.getInt(1);
                    order.setId(orderId);
                    // Luu order detail
                    saveOrderDetails(connection, orderId, order.getItems());
                }
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Order getOrderById(int id) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int orderId = resultSet.getInt("id");
                int customerId = resultSet.getInt("customer_id");
                int restaurantId = resultSet.getInt("restaurant_id");
                int shipperId = resultSet.getInt("shipper_id");
                String customerNote = resultSet.getString("customer_note");
                String status = resultSet.getString("status");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                Order order = new Order();
                order.setId(orderId);
                order.setCustomerId(customerId);
                order.setRestaurantId(restaurantId);
                order.setShipperId(shipperId);
                order.setCustomerNote(customerNote);
                order.setStatus(status);
                order.setCreatedAt(createdAt);
                return order;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void saveOrderDetails(Connection connection, int orderId, List<CartItem> items) {
        if (items == null || items.isEmpty()) {
            return; // Không làm gì nếu danh sách rỗng
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ODER_DETAIL);
            for (CartItem item : items) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, item.getFood().getId());
                preparedStatement.setInt(3, item.getQuantity());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
