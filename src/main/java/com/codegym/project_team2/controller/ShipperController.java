package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.DeliveryItem;
import com.codegym.project_team2.model.DishOption;
import com.codegym.project_team2.service.DeliveryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            case "list":
            default:
                showOverview(request,response);
        }
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
        DeliveryService deliveryService = new DeliveryService();
        List<DeliveryItem> deliveryItems = deliveryService.getDeliveryItems(11);
        request.setAttribute("deliveryItems", deliveryItems);
        request.getRequestDispatcher("/view/shipper/overview/overview.jsp").forward(request,response);

    }
}
