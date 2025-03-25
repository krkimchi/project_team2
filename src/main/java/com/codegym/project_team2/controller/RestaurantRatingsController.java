package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.RestaurantRating;
import com.codegym.project_team2.util.BaseRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "RestaurantRatingsController", value = "/restaurant-ratings")
public class RestaurantRatingsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RestaurantRating> ratings = new ArrayList<>();
        try (Connection conn = BaseRepository.getConnectDB()) {
            PreparedStatement ps = conn.prepareStatement(
                    "select r.restaurant_name, avg(rev.rating) as averageRating, count(*) as reviewCount " +
                            "from reviews rev join orders o on rev.order_id = o.id " +
                            "join restaurants r on o.restaurant_id = r.id " +
                            "where rev.target_type = 'restaurant' " +
                            "group by r.restaurant_name " +
                            "order by r.restaurant_name, avg(rev.rating) desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ratings.add(new RestaurantRating(
                        rs.getString("restaurant_name"),
                        rs.getDouble("averageRating"),
                        rs.getInt("reviewCount")
                ));
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error!", e);
        }
        request.setAttribute("ratings", ratings);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/restaurant/ratings.jsp");
        dispatcher.forward(request, response);
    }
}
