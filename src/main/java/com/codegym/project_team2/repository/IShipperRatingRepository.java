package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.ShipperRating;

import java.sql.SQLException;
import java.util.List;

public interface IShipperRatingRepository {
    List<ShipperRating> getAllRatings() throws SQLException;
}
