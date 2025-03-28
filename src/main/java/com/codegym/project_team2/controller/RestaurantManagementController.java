package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.Restaurant;
import com.codegym.project_team2.repository.RestaurantManagementRepository;
import com.codegym.project_team2.repository.IRestaurantManagementRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RestaurantManagementController", value = "/restaurant-management")
public class RestaurantManagementController extends HttpServlet {

    private IRestaurantManagementRepository restaurantRepository;

    @Override
    public void init() throws ServletException {
        restaurantRepository = new RestaurantManagementRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        int page = 1;
        int restaurantsPerPage = 5;

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        try {
            List<Restaurant> restaurants;
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                restaurants = restaurantRepository.searchRestaurants(searchQuery);
            } else {
                restaurants = restaurantRepository.getAllRestaurants();
            }

            int startIndex = (page - 1) * restaurantsPerPage;
            int endIndex = Math.min(startIndex + restaurantsPerPage, restaurants.size());

            List<Restaurant> paginatedRestaurants = restaurants.subList(startIndex, endIndex);

            int totalPages = (int) Math.ceil((double) restaurants.size() / restaurantsPerPage);

            request.setAttribute("restaurants", paginatedRestaurants);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("searchQuery", searchQuery);

            request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while fetching the restaurant list.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("update".equals(action)) {
                updateRestaurant(request, response);
            } else if ("delete".equals(action)) {
                deleteRestaurant(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while processing the request.");
        }
    }

    private void updateRestaurant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("restaurant_name");
        String address = request.getParameter("restaurant_address");
        String phone = request.getParameter("restaurant_phone");
        String currentPage = request.getParameter("currentPage");
        String searchQuery = request.getParameter("search");

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("message", "Restaurant name cannot be empty.");
            request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
            return;
        }

        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("message", "Restaurant address cannot be empty.");
            request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
            return;
        }

        if (phone == null || phone.trim().isEmpty()) {
            request.setAttribute("message", "Restaurant phone cannot be empty.");
            request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
            return;
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setRestaurantName(name);
        restaurant.setRestaurantAddress(address);
        restaurant.setRestaurantPhone(phone);

        boolean isUpdated = restaurantRepository.updateRestaurant(restaurant);

        if (isUpdated) {
            request.setAttribute("message", "Restaurant updated successfully.");
        } else {
            request.setAttribute("message", "Failed to update restaurant.");
        }

        List<Restaurant> restaurants = restaurantRepository.getAllRestaurants();
        int restaurantsPerPage = 5;
        int startIndex = (Integer.parseInt(currentPage) - 1) * restaurantsPerPage;
        int endIndex = Math.min(startIndex + restaurantsPerPage, restaurants.size());

        List<Restaurant> paginatedRestaurants = restaurants.subList(startIndex, endIndex);
        int totalPages = (int) Math.ceil((double) restaurants.size() / restaurantsPerPage);

        request.setAttribute("restaurants", paginatedRestaurants);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
    }

    private void deleteRestaurant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String currentPage = request.getParameter("currentPage");
        String searchQuery = request.getParameter("search");

        boolean isDeleted = restaurantRepository.deleteRestaurant(id);

        if (isDeleted) {
            request.setAttribute("message", "Restaurant deleted successfully.");
        } else {
            request.setAttribute("message", "Failed to delete restaurant.");
        }

        List<Restaurant> restaurants = restaurantRepository.getAllRestaurants();
        int restaurantsPerPage = 5;
        int startIndex = (Integer.parseInt(currentPage) - 1) * restaurantsPerPage;
        int endIndex = Math.min(startIndex + restaurantsPerPage, restaurants.size());

        List<Restaurant> paginatedRestaurants = restaurants.subList(startIndex, endIndex);
        int totalPages = (int) Math.ceil((double) restaurants.size() / restaurantsPerPage);

        request.setAttribute("restaurants", paginatedRestaurants);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
    }
}
