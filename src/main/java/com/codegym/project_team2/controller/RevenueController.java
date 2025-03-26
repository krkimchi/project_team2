package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.RestaurantRevenue;
import com.codegym.project_team2.model.ShipperRevenue;
import com.codegym.project_team2.repository.IRevenueRepository;
import com.codegym.project_team2.repository.RevenueRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet (name = "RevenueController", value = "/revenue")
public class RevenueController extends HttpServlet {
    private IRevenueRepository revenueRepository;

    @Override
    public void init() throws ServletException {
        revenueRepository = new RevenueRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<RestaurantRevenue> topRestaurantRevenues = revenueRepository.getTop20Restaurants();
            request.setAttribute("topRestaurants", topRestaurantRevenues);

            List<ShipperRevenue> topShippers = revenueRepository.getTop20Shippers();
            request.setAttribute("topShippers", topShippers);

            request.getRequestDispatcher("/view/admin/revenue.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
