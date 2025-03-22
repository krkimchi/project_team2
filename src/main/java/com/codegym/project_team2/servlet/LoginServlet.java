package com.codegym.project_team2.servlet;

import com.codegym.project_team2.model.User;
import com.codegym.project_team2.repository.UserRepository;
import com.codegym.project_team2.service.IUserService;
import com.codegym.project_team2.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private IUserService userService = new UserService(new UserRepository());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        User user = userService.login(username);

        if (user != null) {
            response.sendRedirect("/index.jsp");
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
