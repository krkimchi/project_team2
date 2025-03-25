package com.codegym.project_team2.model;

import com.codegym.project_team2.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private IFoodService foodController = new FoodService();
    private IOrderService orderController = new OrderService();
    private IRatingService ratingController = new RatingService();

    private List<Order> orderHistory = new ArrayList<>(); // Lịch sử đơn hàng
    private List<CartItem> cart = new ArrayList<>(); // Giỏ hàng

    public Customer() {
        super();
    }

    public Customer(int id, String username, String password, String email, String phone, String fullName, String avatarUrl, UserType userType, boolean isActive, LocalDateTime createdAt, List<Order> orderHistory, List<CartItem> cart) {
        super(id, username, password, email, phone, fullName, avatarUrl, userType, isActive, createdAt);
        this.orderHistory = orderHistory;
        this.cart = cart;
    }

    // Xem danh sách món ăn được đặt nhiều nhất ( hiển thị ở đầu trang chủ )
    public List<DishDto> getMostOrderedFoods() {
        return foodController.getMostOrderedFoods();
    }

    // Tìm kiếm món ăn theo tên hoặc nhà hàng (GV yêu cầu )
    public List<DishDto> searchFood(String keyword) {
        return foodController.searchFood(keyword);
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    // Thêm món ăn vào giỏ
    public void addToCart(Food food, int quantity) {
        for (CartItem item : cart) {
            if (item.getFood().equals(food)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        CartItem item = new CartItem(food, quantity);
        cart.add(item);
    }

    // Chỉnh sửa số lượng món ăn trong giỏ ( giỏ hàng dùng session )
    public void updateCartQuantity(Food food, int quantity) {
        for (CartItem item : cart) {
            if (item.getDishId() == food.getId()) {
                item.setQuantity(quantity);
            }
        }
    }

    // Đặt đơn hàng ( GV yêu cầu )
    public boolean placeOrder() {
        Order order = new Order(this, cart);
        return orderController.save(order);
    }

    // Xem lich su dat hang
    public List<Order> viewOrderHistory() {
        return orderHistory;
    }

    public boolean rateFood(int foodId, int rating) {
        return ratingController.rateFood(foodId, rating);
    }

    public boolean rateRestaurant(int restaurantId, int rating) {
        return ratingController.rateRestaurant(restaurantId, rating);
    }

    public boolean rateShipper(int shipperId, int rating) {
        return ratingController.rateShipper(shipperId, rating);
    }
}
