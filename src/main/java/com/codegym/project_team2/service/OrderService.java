package com.codegym.project_team2.service;

import com.codegym.project_team2.dto.OrderDto;
import com.codegym.project_team2.model.Order;
import com.codegym.project_team2.repository.IOrderRepository;
import com.codegym.project_team2.repository.OrderRepository;

import java.util.List;

public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository = new OrderRepository();

    @Override
    public boolean save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Integer userId) {
        return orderRepository.getOrdersByUserId(userId);
    }
}
