package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.User;
import com.codegym.project_team2.repository.UserRepository;
import com.codegym.project_team2.service.IUserService;
import com.codegym.project_team2.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    private IUserService userService = new UserService(new UserRepository());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            String userType = String.valueOf(user.getUserType());
            session.setAttribute("user", user);
            session.setAttribute("userType", user.getUserType());
            switch (userType) {
                case "admin":
                    break;
                case "owner":
                    response.sendRedirect("/view/owner/owner.jsp");
                    break;
                case "shipper":
                    break;
                default:
                    response.sendRedirect("/index.jsp");
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/view/user/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);
    }
}
