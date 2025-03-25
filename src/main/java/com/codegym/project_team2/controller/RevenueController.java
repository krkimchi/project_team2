package com.codegym.project_team2.controller;

import com.codegym.project_team2.repository.IRevenueRepository;
import com.codegym.project_team2.repository.RevenueRepository;
import com.codegym.project_team2.model.Revenue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet (name = "RevenueController", value = "/restaurant-revenue")
public class RevenueController extends HttpServlet {
    private IRevenueRepository revenueRepository;

    @Override
    public void init() throws ServletException {
        revenueRepository = new RevenueRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Revenue> topRevenues = revenueRepository.getTop20Restaurants();
            request.setAttribute("topRevenues", topRevenues);
            request.getRequestDispatcher("/view/restaurant/revenue.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
