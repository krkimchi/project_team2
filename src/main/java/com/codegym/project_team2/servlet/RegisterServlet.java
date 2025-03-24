package com.codegym.project_team2.servlet;

import com.codegym.project_team2.model.User;
import com.codegym.project_team2.repository.UserRepository;
import com.codegym.project_team2.service.IUserService;
import com.codegym.project_team2.service.UserService;
import com.codegym.project_team2.util.EmailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private IUserService userService = new UserService(new UserRepository());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String fullName = request.getParameter("full_name");
        String userTypeString = request.getParameter("userType");

        User.UserType userType = User.UserType.valueOf(userTypeString.toUpperCase());

        User user = new User(0, username, password, email, phone, fullName, null, userType, true, LocalDateTime.now());

        String otp = generateOtp();

        request.getSession().setAttribute("otp", otp);
        request.setAttribute("otp", otp);

        EmailService.sendEmail(email, otp);

        boolean isRegistered = userService.register(user);

        if (isRegistered) {
            request.getRequestDispatcher("/view/user/register.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Registration failed");
            request.getRequestDispatcher("/view/user/register.jsp").forward(request, response);
        }
    }

    private String generateOtp() {
        Random rand = new Random();
        int otp = rand.nextInt(999999 - 100000) + 100000;
        return String.valueOf(otp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/user/register.jsp").forward(req, resp);
    }
}
