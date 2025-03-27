package com.codegym.project_team2.controller;

import com.codegym.project_team2.dto.RestaurantDto;
import com.codegym.project_team2.model.User;
import com.codegym.project_team2.service.IRestaurantService;
import com.codegym.project_team2.service.RestaurantService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RestaurantController", value = "/restaurants")
public class RestaurantController extends HttpServlet {
    IRestaurantService restaurantService = new RestaurantService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //lay du lieu action
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "openRestaurant":
                openRestaurant(req, resp);
                break;
            case "closeRestaurant":
                closeRestaurant(req, resp);
                break;
        }
    }

    private void openRestaurant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        restaurantService.open(userId);
        RestaurantDto restaurant = restaurantService.show(userId);
        req.setAttribute("restaurant", restaurant);
        req.getRequestDispatcher("/view/owner/restaurant.jsp").forward(req, resp);
    }

    private void closeRestaurant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        restaurantService.close(userId);
        RestaurantDto restaurant = restaurantService.show(userId);
        req.setAttribute("restaurant", restaurant);
        req.getRequestDispatcher("/view/owner/restaurant.jsp").forward(req, resp);
    }
}
