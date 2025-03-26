package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.User;
import com.codegym.project_team2.repository.UserManagementRepository;
import com.codegym.project_team2.repository.IUserManagementRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserManagementController", value = "/user-management")
public class UserManagementController extends HttpServlet {

    private IUserManagementRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserManagementRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        int page = 1;
        int usersPerPage = 5;

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        try {
            List<User> users;
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                users = userRepository.searchUsers(searchQuery);
            } else {
                users = userRepository.getAllUsers();
            }

            int startIndex = (page - 1) * usersPerPage;
            int endIndex = Math.min(startIndex + usersPerPage, users.size());

            List<User> paginatedUsers = users.subList(startIndex, endIndex);

            int totalPages = (int) Math.ceil((double) users.size() / usersPerPage);

            request.setAttribute("users", paginatedUsers);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("searchQuery", searchQuery);

            request.getRequestDispatcher("/view/admin/user-management.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while fetching the user list.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("update".equals(action)) {
                updateUser(request, response);
            } else if ("delete".equals(action)) {
                deleteUser(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while processing the request.");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String fullName = request.getParameter("full_name");
        String currentPage = request.getParameter("currentPage");
        String searchQuery = request.getParameter("search");

        if (email == null || email.trim().isEmpty() || phone == null || phone.trim().isEmpty() || fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("message", "Email, phone, and full name cannot be empty.");
            request.getRequestDispatcher("/view/admin/user-management.jsp").forward(request, response);
            return;
        }

        User user = userRepository.getAllUsers().stream()
                .filter(u -> u.getId() == id)
                .findFirst().orElse(null);

        if (user == null) {
            request.setAttribute("message", "User not found.");
            request.getRequestDispatcher("/view/admin/user-management.jsp").forward(request, response);
            return;
        }

        user.setEmail(email);
        user.setPhone(phone);
        user.setFullName(fullName);

        boolean isUpdated = userRepository.updateUser(user);

        if (isUpdated) {
            request.setAttribute("message", "User updated successfully.");
        } else {
            request.setAttribute("message", "Failed to update user.");
        }

        List<User> users = userRepository.getAllUsers();
        int usersPerPage = 5;
        int startIndex = (Integer.parseInt(currentPage) - 1) * usersPerPage;
        int endIndex = Math.min(startIndex + usersPerPage, users.size());

        List<User> paginatedUsers = users.subList(startIndex, endIndex);
        int totalPages = (int) Math.ceil((double) users.size() / usersPerPage);

        request.setAttribute("users", paginatedUsers);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/view/admin/user-management.jsp").forward(request, response);
    }


    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String currentPage = request.getParameter("currentPage");
        String searchQuery = request.getParameter("search");

        boolean isDeleted = userRepository.deleteUser(id);

        if (isDeleted) {
            request.setAttribute("message", "User deleted successfully.");
        } else {
            request.setAttribute("message", "Failed to delete user.");
        }

        List<User> users = userRepository.getAllUsers();
        int usersPerPage = 5;
        int startIndex = (Integer.parseInt(currentPage) - 1) * usersPerPage;
        int endIndex = Math.min(startIndex + usersPerPage, users.size());

        List<User> paginatedUsers = users.subList(startIndex, endIndex);
        int totalPages = (int) Math.ceil((double) users.size() / usersPerPage);

        request.setAttribute("users", paginatedUsers);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);

        request.getRequestDispatcher("/view/admin/user-management.jsp").forward(request, response);
    }
}
