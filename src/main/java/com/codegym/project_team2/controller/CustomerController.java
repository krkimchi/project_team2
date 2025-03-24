package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.Customer;
import com.codegym.project_team2.model.DishDto;
import com.codegym.project_team2.model.Food;
import com.codegym.project_team2.service.FoodService;
import com.codegym.project_team2.service.IFoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerController extends HttpServlet {
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
                showCart(req, resp);
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

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/customer/cart.jsp").forward(req, resp);
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter("searchName");
        List<DishDto> foodList = foodService.searchFood(searchName);
        req.setAttribute("foodList", foodList);
        req.getRequestDispatcher("/view/customer/search.jsp").forward(req, resp);
    }

    private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DishDto> foodList = foodService.getMostOrderedFoods();
        req.setAttribute("foodList", foodList);
        req.getRequestDispatcher("/view/customer/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add_to_cart":
                addToCart(req, resp);
                break;
        }
    }

    private void submitShipperRating(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void submitDishRating(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void placeOrder(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String dishId = req.getParameter("dish_id");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        // Kiểm tra nếu dishId hợp lệ
        if (dishId != null && !dishId.isEmpty()) {
            int id = Integer.parseInt(dishId);  // Chuyển đổi ID sang kiểu int
            Food food = foodService.getFoodById(id); // Lấy món ăn từ dịch vụ (tạo phương thức này trong foodService)

            // Lấy thông tin khách hàng từ session
            Customer customer = (Customer) req.getSession().getAttribute("customer");

            // Thêm món ăn vào giỏ hàng
            if (customer != null) {
                customer.addToCart(food, quantity);  // Gọi phương thức addToCart của Customer
            }

            // Thông báo thành công bằng alert và chuyển hướng
            resp.sendRedirect("/customer?action=cart");  // Chuyển hướng đến trang giỏ hàng
        } else {
            // Nếu không có dishId hợp lệ, chuyển hướng về trang chủ
            resp.sendRedirect("/customer");
        }
    }

}
