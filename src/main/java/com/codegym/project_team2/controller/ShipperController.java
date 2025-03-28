package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.DeliveryItem;
import com.codegym.project_team2.model.DishOption;
import com.codegym.project_team2.model.User;
import com.codegym.project_team2.service.DeliveryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShipperController", value = "/shipper")
public class ShipperController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delivery_detail":
                showDeliveryDetail(request,response);
                break;
            case "listorder":
                showListOrders(request,response);
                break;
            case "profile":
                showProfile(request, response);
                break;
            case "deliver":
                updateOrder(request,response);
                break;
            case "finish":
                updateOrder(request,response);
                break;
            case "confirm":
                updateOrder(request,response);
                break;
            case "history":
                showHistory(request,response);
            default:
                showOverview(request,response);
        }
    }

    private void showHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        DeliveryService deliveryService = new DeliveryService();
        List<DeliveryItem> deliveryItems = deliveryService.getDeliveryItems(userId);
        int ordersHistory = deliveryService.showNumbersOfOrdersHistory(userId);
        request.setAttribute("ordersHistory", ordersHistory);
        request.setAttribute("deliveryItems", deliveryItems);
        request.getRequestDispatcher("/view/shipper/orders_history.jsp").forward(request,response);
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        DeliveryService deliveryService = new DeliveryService();
        if (action.equals("confirm")) {
            deliveryService.changeDeliverystatus(id, "confirmed");
        } else if (action.equals("deliver")) {
            deliveryService.changeDeliverystatus(id, "delivering");
        } else if (action.equals("finish")) {
            deliveryService.changeDeliverystatus(id, "completed");
        }
        DeliveryItem deliveryItem = deliveryService.getDeliveryItemById(id);
        List<DishOption> dishOption = deliveryService.getDishesWithOption(id);
        request.setAttribute("deliveryItem", deliveryItem);
        request.setAttribute("dishOption", dishOption);
        request.getRequestDispatcher("/view/shipper/delivery_detail.jsp").forward(request,response);

    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/view/shipper/account_detail.jsp");
    }

    private void showListOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        DeliveryService deliveryService = new DeliveryService();
        List<DeliveryItem> ordersList = deliveryService.getDeliveryItems(userId);
        request.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher("/view/shipper/orders/orders.jsp").forward(request,response);
    }

    private void showDeliveryDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DeliveryService deliveryService = new DeliveryService();
        DeliveryItem deliveryItem = deliveryService.getDeliveryItemById(id);
        List<DishOption> dishOption = deliveryService.getDishesWithOption(id);
        request.setAttribute("deliveryItem", deliveryItem);
        request.setAttribute("dishOption", dishOption);
        request.getRequestDispatcher("/view/shipper/delivery_detail.jsp").forward(request,response);
    }

    private void showOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        DeliveryService deliveryService = new DeliveryService();
        List<DeliveryItem> deliveryItems = deliveryService.getDeliveryItems(userId);
        List<DeliveryItem> currentItems = new ArrayList<>();
        for (DeliveryItem deliveryItem : deliveryItems) {
            if (!deliveryItem.getDeliveryStatus().equals("completed")&& !deliveryItem.getDeliveryStatus().equals("cancelled")) {
                currentItems.add(deliveryItem);
            }
        }
        int ordersHistory = deliveryService.showNumbersOfOrdersHistory(userId);
        int currentOrders = currentItems.size();
        request.setAttribute("deliveryItems", currentItems);
        request.setAttribute("ordersHistory", ordersHistory);
        request.setAttribute("currentOrders", currentOrders);
        request.getRequestDispatcher("/view/shipper/overview/overview.jsp").forward(request,response);
    }
}
