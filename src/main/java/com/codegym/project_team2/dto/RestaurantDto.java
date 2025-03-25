package com.codegym.project_team2.dto;

import java.time.LocalDateTime;

public class RestaurantDto {
    private int id;
    private String ownerName;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantLogo;
    private boolean isOpen;
    private LocalDateTime createdAt;

    public RestaurantDto() {
    }

    public RestaurantDto(int id, String ownerName, String restaurantName, String restaurantAddress,
                         String restaurantPhone, String restaurantLogo, boolean isOpen, LocalDateTime createdAt) {
        this.id = id;
        this.ownerName = ownerName;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhone = restaurantPhone;
        this.restaurantLogo = restaurantLogo;
        this.isOpen = isOpen;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantLogo() {
        return restaurantLogo;
    }

    public void setRestaurantLogo(String restaurantLogo) {
        this.restaurantLogo = restaurantLogo;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
