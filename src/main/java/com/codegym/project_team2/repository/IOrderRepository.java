package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Order;

public interface IOrderRepository {
    boolean save(Order order);
}
