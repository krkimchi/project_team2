package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.RestaurantRevenue;
import com.codegym.project_team2.model.ShipperRevenue;

import java.sql.SQLException;
import java.util.List;

public interface IRevenueRepository {
    List<RestaurantRevenue> getTop20Restaurants() throws SQLException;

    List<ShipperRevenue> getTop20Shippers() throws SQLException;
}
