package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.DishDto;
import com.codegym.project_team2.service.FoodService;
import com.codegym.project_team2.service.IFoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private IFoodService foodService = new FoodService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "search_dishes":
                showSearch(req, resp);
                break;
            case "cart":
                showFormCart(req, resp);
                break;
            case "order_history":
                showOrderHistory(req, resp);
            case "profile":
                showProfile(req, resp);
                break;
            case "logout":
                showLogout(req, resp);
                break;
            default:
                showHomePage(req, resp);
                break;

        }
    }

    private void showLogout(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showOrderHistory(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showFormCart(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter("searchName");
        List<DishDto> foodList = foodService.searchFood(searchName);
        req.setAttribute("foodList", foodList);
        req.getRequestDispatcher("/views/customer/search.jsp").forward(req, resp);
    }

    private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DishDto> foodList = foodService.getMostOrderedFoods();
        req.setAttribute("foodList", foodList);
        req.getRequestDispatcher("/views/customer/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void submitShipperRating(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void submitDishRating(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void placeOrder(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) {
    }
}
