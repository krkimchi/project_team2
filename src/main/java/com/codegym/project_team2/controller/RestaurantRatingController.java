package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.RestaurantRating;
import com.codegym.project_team2.repository.RestaurantRatingRepository;
import com.codegym.project_team2.repository.IRestaurantRatingRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RestaurantRatingController", value = "/restaurant-rating")
public class RestaurantRatingController extends HttpServlet {

    private IRestaurantRatingRepository restaurantRatingRepository = new RestaurantRatingRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RestaurantRating> ratings = null;
        try {
            ratings = restaurantRatingRepository.getAllRatings();
        } catch (SQLException e) {
            throw new ServletException("Database access error!", e);
        }
        request.setAttribute("ratings", ratings);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/restaurant/ratings.jsp");
        dispatcher.forward(request, response);
    }
}
