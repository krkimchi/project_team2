package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.ShipperRating;
import com.codegym.project_team2.repository.ShipperRatingRepository;
import com.codegym.project_team2.repository.IShipperRatingRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShipperRatingController", value = "/shipper-rating")
public class ShipperRatingController extends HttpServlet {

    private IShipperRatingRepository shipperRatingRepository = new ShipperRatingRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ShipperRating> ratings = null;
        try {
            ratings = shipperRatingRepository.getAllRatings();
        } catch (SQLException e) {
            throw new ServletException("Database access error!", e);
        }
        request.setAttribute("ratings", ratings);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/shipper-rating.jsp");
        dispatcher.forward(request, response);
    }
}

