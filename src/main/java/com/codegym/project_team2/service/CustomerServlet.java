package com.codegym.project_team2.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "search_dishes":
                showFormSearch(req, resp);
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

    private void showFormSearch(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/customer/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
