package com.codegym.project_team2.controller;

import com.codegym.project_team2.model.*;
import com.codegym.project_team2.dto.DishDto;
import com.codegym.project_team2.repository.IUserRepository;
import com.codegym.project_team2.repository.UserRepository;
import com.codegym.project_team2.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerController extends HttpServlet {
    private IFoodService foodService = new FoodService();
    private IUserRepository userRepository = new UserRepository();
    private IOrderService orderService = new OrderService();
    private IDeliveryService deliveryService = new DeliveryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "search_dishes":
                showSearch(req, resp);
                break;

            case "cart":
                viewCart(req, resp);
                break;

            case "order_history":
                showOrderHistory(req, resp);
                break;

            case "profile":
                showProfile(req, resp);
                break;

            case "update_cart":
                updateCart(req, resp);
                break;

            case "remove_from_cart":
                removeFromCart(req, resp);
                break;

            case "order_recent":
                viewRecentOrder(req, resp);
                break;

            default:
                showHomePage(req, resp);
                break;

        }
    }

    private void viewRecentOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra xem người dùng đã đăng nhập chưa
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        if (customer == null) {
            req.getSession().setAttribute("error", "Vui lòng đăng nhập để xem đơn hàng.");
            resp.sendRedirect("/login");
            return;
        }
        String recentOrderId = (String) req.getSession().getAttribute("recentOrderId");
        Order recentOrder = null;

        if (recentOrderId != null ) {
            int orderId = Integer.parseInt(recentOrderId);
            recentOrder = orderService.getOrderById(orderId);
            List<CartItem> cartItemList = foodService.getCartItemsByOrderId(orderId);
            if (recentOrder != null) {
                // Gán cartItemList vào recentOrder
                recentOrder.setItems(cartItemList);
                // Lưu recentOrder vào request scope
                req.setAttribute("recentOrder", recentOrder);
                // Không cần lưu cartItemList vào session scope nữa, vì đã gán vào recentOrder
                // req.getSession().setAttribute("cartItemList", cartItemList);
            }
        }

        req.getRequestDispatcher("/view/customer/recent_order.jsp").forward(req, resp);
    }

    private void updateCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String foodId = req.getParameter("food_id");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Customer customer = (Customer) req.getSession().getAttribute("customer");
        if (customer != null && foodId != null) {
            Food food = foodService.getFoodById(Integer.parseInt(foodId));
            if (food != null) {
                // Nếu số lượng <= 0, xóa món khỏi giỏ hàng
                if (quantity <= 0) {
                    customer.getCart().removeIf(item -> item.getFood().equals(food));
                } else {
                    customer.updateCartQuantity(food, quantity);
                }
            }
        }
        resp.sendRedirect("/customer?action=cart");
    }

    private void removeFromCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String foodId = req.getParameter("food_id");

        Customer customer = (Customer) req.getSession().getAttribute("customer");
        if (customer != null && foodId != null) {
            Food food = foodService.getFoodById(Integer.parseInt(foodId));
            if (food != null) {
                customer.getCart().removeIf(item -> item.getFood().equals(food));
            }
        }
        resp.sendRedirect("/customer?action=cart");
    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        if (customer == null) {
            req.getSession().setAttribute("error", "Vui lòng đăng nhập để xem thông tin cá nhân.");
            resp.sendRedirect("/login");
            return;
        }

        User user = userRepository.getUserByUserName(customer.getUsername());
        if (user != null) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("/view/customer/profile.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("error", "Không tìm thấy thông tin người dùng.");
            resp.sendRedirect("/customer");
        }
    }

    private void showOrderHistory(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void viewCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        if (customer == null) {
            resp.sendRedirect("/login");
            return;
        }

        double total = 0;
        for (CartItem item : customer.getCart()) {
            total += item.getFood().getPrice() * item.getQuantity();
        }
        req.setAttribute("total", total);
        req.getRequestDispatcher("/view/customer/cart.jsp").forward(req, resp);
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter("searchName");
        List<DishDto> foodList = foodService.searchFood(searchName);
        req.setAttribute("foodList", foodList);
        req.getRequestDispatcher("/view/customer/search.jsp").forward(req, resp);
    }

    private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DishDto> foodList = foodService.getMostOrderedFoods();
        req.setAttribute("foodList", foodList);
        req.getRequestDispatcher("/view/customer/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add_to_cart":
                addToCart(req, resp);
                break;

            case "place_order":
                placeOrder(req, resp);
                break;

            case "logout":
                doLogout(req, resp);
                break;
        }
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("customer");
        req.getSession().invalidate();
        resp.sendRedirect("/login");
    }

    private void submitShipperRating(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void submitDishRating(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String dishId = req.getParameter("dish_id");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        // Kiểm tra nếu dishId hợp lệ
        if (dishId != null && !dishId.isEmpty()) {
            int id = Integer.parseInt(dishId);
            Food food = foodService.getFoodById(id);

            Customer customer = (Customer) req.getSession().getAttribute("customer");
            if (customer != null && food != null) {
                customer.addToCart(food, quantity);
                // Lưu thông báo vào session
                req.getSession().setAttribute("message", "Món ăn " + food.getName() + " đã được thêm vào giỏ hàng.");
            } else {
                req.getSession().setAttribute("error", "Không thể thêm món vào giỏ hàng. Vui lòng đăng nhập hoặc thử lại.");
            }

            // Chuyển hướng về trang hiện tại (trang chủ)
            resp.sendRedirect("/customer");
        } else {
            req.getSession().setAttribute("error", "Mã món ăn không hợp lệ.");
            resp.sendRedirect("/customer");
        }
    }

    private void placeOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        if (customer != null && !customer.getCart().isEmpty()) {
            // Lấy ghi chú từ request
            String note = req.getParameter("note");
            if (note == null) {
                note = "";
            }

            // Tạo đơn hàng
            Order order = new Order(customer.getId(), customer.getCart(), deliveryService.shippersOrdersCount());
            order.setCustomerNote(note);

            // Kiểm tra xem tất cả món trong giỏ hàng có cùng restaurantId không
            int restaurantId = order.getRestaurantId();
            boolean allSameRestaurant = true;
            for (CartItem item : customer.getCart()) {
                if (item.getFood().getRestaurantId() != restaurantId) {
                    allSameRestaurant = false;
                    break;
                }
            }

            if (!allSameRestaurant) {
                req.getSession().setAttribute("error", "Tất cả món trong giỏ hàng phải từ cùng một nhà hàng!");
                resp.sendRedirect("/customer?action=cart");
                return;
            }

            // Lưu đơn hàng
            boolean success = customer.placeOrder(order);
            if (success) {
                req.getSession().setAttribute("recentOrderId", String.valueOf(order.getId()));
                customer.getCart().clear();
                req.getSession().setAttribute("message", "Đặt hàng thành công!");
            } else {
                req.getSession().setAttribute("error", "Đặt hàng thất bại. Vui lòng thử lại.");
            }
        } else {
            req.getSession().setAttribute("error", "Giỏ hàng trống hoặc bạn chưa đăng nhập.");
        }
        resp.sendRedirect("/customer?action=cart");
    }

}
