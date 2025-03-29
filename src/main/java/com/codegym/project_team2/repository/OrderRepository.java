package com.codegym.project_team2.repository;

import com.codegym.project_team2.dto.OrderDto;
import com.codegym.project_team2.model.CartItem;
import com.codegym.project_team2.model.Food;
import com.codegym.project_team2.model.Order;
import com.codegym.project_team2.model.OrderDetail;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final String ADD_ORDER = "insert into orders(customer_id, restaurant_id, customer_note, status, created_at) values(?, ?, ?, ?, ?)";
    private final String ADD_ODER_DETAIL = "insert into order_detail(order_id, dish_id, dish_quantity) values(?, ?, ?)";
    private final String SELECT_ORDER_BY_ID = "select * from orders o where o.id = ?";
    private final String SELECT_ORDER_DETAIL_BY_ID = "SELECT od.dish_id, od.dish_quantity, d.dish_name " +
            "FROM order_detail od " +
            "LEFT JOIN dishes d ON od.dish_id = d.id " +
            "WHERE od.order_id = ?";
    ;
    private final String SELECT_ORDERS_BY_USER_ID =
            "SELECT o.*, r.restaurant_name, u.full_name AS shipper_name, " +
                    "SUM(od.dish_quantity * d.dish_price + IFNULL(do.option_price, 0)) AS total_price " +
                    "FROM orders o " +
                    "LEFT JOIN restaurants r ON o.restaurant_id = r.id " +
                    "LEFT JOIN users u ON o.shipper_id = u.id AND u.user_type = 'shipper' " +
                    "LEFT JOIN order_detail od ON o.id = od.order_id " +
                    "LEFT JOIN dishes d ON od.dish_id = d.id " +
                    "LEFT JOIN order_option_detail ood ON od.id = ood.order_detail_id " +
                    "LEFT JOIN dish_options do ON ood.dish_option_id = do.id " +
                    "WHERE o.customer_id = ? " +
                    "GROUP BY o.id " +
                    "ORDER BY o.created_at DESC";

    @Override
    public boolean save(Order order) {
        try {
            Connection connection = BaseRepository.getConnectDB();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setInt(2, order.getRestaurantId());
            preparedStatement.setString(3, order.getCustomerNote());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setObject(5, order.getCreatedAt());

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

    @Override
    public List<OrderDto> getOrdersByUserId(int userId) {
        List<OrderDto> orders = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("id");
                int customerId = resultSet.getInt("customer_id");
                int restaurantId = resultSet.getInt("restaurant_id");
                int shipperId = resultSet.getInt("shipper_id");
                String customerNote = resultSet.getString("customer_note");
                String status = resultSet.getString("status");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                String restaurantName = resultSet.getString("restaurant_name");
                String shipperName = resultSet.getString("shipper_name");
                Double totalPrice = Double.valueOf(resultSet.getString("total_price"));
                OrderDto orderDto = new OrderDto(orderId, customerId, restaurantId, shipperId, customerNote, status, createdAt, restaurantName, shipperName, totalPrice);
                orderDto.setOrderDetails(getOrderDetails(orderId, connection));
                orders.add(orderDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    private List<OrderDetail> getOrderDetails(int orderId, Connection connection) throws SQLException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_DETAIL_BY_ID);
        preparedStatement.setInt(1, orderId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int dishId = resultSet.getInt("dish_id");
            int dishQuantity = resultSet.getInt("dish_quantity");
            String dishName = resultSet.getString("dish_name");

            OrderDetail orderDetail = new OrderDetail(orderId, dishId, dishQuantity);
            orderDetail.setDishName(dishName);
            orderDetails.add(orderDetail);
        }

        return orderDetails;
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
