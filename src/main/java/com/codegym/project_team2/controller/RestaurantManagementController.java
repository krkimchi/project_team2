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

@WebServlet("/restaurant-management")
public class RestaurantManagementController extends HttpServlet {

    private IRestaurantManagementRepository restaurantRepository;

    @Override
    public void init() throws ServletException {
        restaurantRepository = new RestaurantManagementRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");

        try {
            List<Restaurant> restaurants;
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                // Tìm kiếm nhà hàng theo tên hoặc ID
                restaurants = restaurantRepository.searchRestaurants(searchQuery);
            } else {
                // Lấy tất cả nhà hàng nếu không có tìm kiếm
                restaurants = restaurantRepository.getAllRestaurants();
            }

            // Đưa dữ liệu nhà hàng vào request và chuyển tiếp đến JSP
            request.setAttribute("restaurants", restaurants);
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

        // Kiểm tra nếu trường restaurant_name bị null hoặc trống
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("message", "Restaurant name cannot be empty.");
            request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
            return;
        }

        // Kiểm tra nếu trường restaurant_address bị null hoặc trống
        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("message", "Restaurant address cannot be empty.");
            request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
            return;
        }

        // Kiểm tra nếu trường restaurant_phone bị null hoặc trống
        if (phone == null || phone.trim().isEmpty()) {
            request.setAttribute("message", "Restaurant phone cannot be empty.");
            request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
            return;
        }

        // Tiến hành cập nhật nhà hàng nếu tất cả trường hợp kiểm tra hợp lệ
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

        // Lấy lại danh sách nhà hàng sau khi cập nhật
        List<Restaurant> restaurants = restaurantRepository.getAllRestaurants();
        request.setAttribute("restaurants", restaurants);

        // Chuyển tiếp lại trang restaurant management để hiển thị lại danh sách và thông báo
        request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
    }

    private void deleteRestaurant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Gọi phương thức deleteRestaurant trong repository để xóa nhà hàng
        boolean isDeleted = restaurantRepository.deleteRestaurant(id);

        // Thông báo kết quả
        if (isDeleted) {
            request.setAttribute("message", "Restaurant deleted successfully.");
        } else {
            request.setAttribute("message", "Failed to delete restaurant.");
        }

        // Lấy lại danh sách nhà hàng sau khi xóa
        List<Restaurant> restaurants = restaurantRepository.getAllRestaurants();
        request.setAttribute("restaurants", restaurants);

        // Chuyển tiếp lại trang restaurant management để hiển thị lại danh sách và thông báo
        request.getRequestDispatcher("/view/admin/restaurant-management.jsp").forward(request, response);
    }

}



