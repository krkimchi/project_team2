package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.Order;
import com.codegym.project_team2.repository.IOrderRepository;
import com.codegym.project_team2.repository.OrderRepository;

public class OrderController implements IOrderController {
    private IOrderRepository orderRepository = new OrderRepository();

    @Override
    public boolean save(Order order) {
        return orderRepository.save(order);
    }
}
