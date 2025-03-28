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

@WebServlet(name = "OwnerController", value = "/owners")
public class OwnerController extends HttpServlet {
    private IRestaurantService restaurantService = new RestaurantService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //lay du lieu action
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showRestaurant":
                showRestaurant(req, resp);
                break;
        }
    }

    private void showRestaurant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        RestaurantDto restaurant = restaurantService.show(userId);
        req.setAttribute("restaurant", restaurant);
        req.getRequestDispatcher("/view/owner/restaurant.jsp").forward(req, resp);
    }


}
