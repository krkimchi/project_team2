package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.Revenue;

import java.sql.SQLException;
import java.util.List;

public interface IRevenueRepository {
    List<Revenue> getTop20Restaurants() throws SQLException;
}
