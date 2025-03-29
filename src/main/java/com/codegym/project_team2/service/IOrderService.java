package com.codegym.project_team2.service;

import com.codegym.project_team2.dto.OrderDto;
import com.codegym.project_team2.model.Order;

import java.util.List;

public interface IOrderService {
    boolean save(Order order);
    Order getOrderById(int id);

    List<OrderDto> getOrdersByUserId(Integer userId);
}
