package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.CartItem;
import com.codegym.project_team2.model.Food;
import com.codegym.project_team2.model.Order;

import java.util.List;

public interface IOrderRepository {
    boolean save(Order order);
    Order getOrderById(int id);
}

