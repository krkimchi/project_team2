package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.DeliveryItem;
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
            case "detail":
                break;
            default:
                showOverview(request,response);
        }
    }

    private void showOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeliveryService deliveryService = new DeliveryService();
        List<DeliveryItem> deliveryItems = deliveryService.getDeliveryItems(11);
        request.setAttribute("deliveryItems", deliveryItems);
        request.getRequestDispatcher("/view/shipper/overview/overview.jsp").forward(request,response);

    }
}
