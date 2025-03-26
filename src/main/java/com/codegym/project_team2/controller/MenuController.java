package com.codegym.project_team2.controller;

import com.codegym.project_team2.dto.RestaurantDto;
import com.codegym.project_team2.model.Dish;
import com.codegym.project_team2.model.User;
import com.codegym.project_team2.service.DishService;
import com.codegym.project_team2.service.RestaurantService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "MenuController", value = "/menus")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1MB
        maxFileSize = 1024 * 1024 * 10,  // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addDish":
                addDish(req, resp);
                break;
        }
    }

    private void addDish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        RestaurantDto restaurant = restaurantService.show(userId);

        Part filePart = req.getPart("dishImg");
        String fileName = String.valueOf(filePart.getSubmittedFileName());
        String path = "/resources/images/food/"+ fileName;
        filePart.write(path);

        String dishName = req.getParameter("dishName");
        float dishPrice = Float.parseFloat(req.getParameter("dishPrice"));
        String dishImg = req.getParameter(fileName);
        String description = req.getParameter("description");
        boolean isAvailable = Boolean.parseBoolean(req.getParameter("isAvailable"));

        Dish dish = new Dish(restaurant.getId(), dishName, dishPrice, dishImg, description, isAvailable);
        dishService.add(dish);

        showMenu(req, resp);
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
