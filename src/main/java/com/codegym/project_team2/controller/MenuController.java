package com.codegym.project_team2.controller;

import com.codegym.project_team2.dto.RestaurantDto;
import com.codegym.project_team2.model.Dish;
import com.codegym.project_team2.model.User;
import com.codegym.project_team2.service.DishService;
import com.codegym.project_team2.service.RestaurantService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "MenuController", value = "/menus")
public class MenuController extends HttpServlet {
    private RestaurantService restaurantService = new RestaurantService();
    private DishService dishService = new DishService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showMenu":
                showMenu(req, resp);
                break;
        }
    }

    private void showMenu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        RestaurantDto restaurant = restaurantService.show(userId);
        List<Dish> dishList = dishService.showByRestaurantId(restaurant.getId());
        req.setAttribute("dishList", dishList);
        req.getRequestDispatcher("/view/owner/menu.jsp").forward(req, resp);
    }
}
