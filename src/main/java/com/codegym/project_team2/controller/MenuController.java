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
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MenuController", value = "/menus")
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
            case "deleteDish":
                deleteDish(req, resp);
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

    private void deleteDish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        dishService.delete(id);

        showMenu(req, resp);
    }

    private void addDish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        RestaurantDto restaurant = restaurantService.show(userId);

        Part filePart = req.getPart("dishImg");
        String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();

        String uploadDir = getServletContext().getRealPath("/resources/images/food/");
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }

        filePart.write(uploadDir + fileName);

        String dishName = req.getParameter("dishName");
        float dishPrice = Float.parseFloat(req.getParameter("dishPrice"));
        String dishImg = fileName;
        String description = req.getParameter("description");

        String isAvailableValue = req.getParameter("isAvailable");
        boolean isAvailable;
        if (isAvailableValue.equals("on")) {
            isAvailable = true;
        } else {
            isAvailable = false;
        }

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
