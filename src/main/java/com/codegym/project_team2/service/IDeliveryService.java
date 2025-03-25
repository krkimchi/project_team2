package com.codegym.project_team2.service;

import com.codegym.project_team2.model.DeliveryItem;

import java.util.List;

public interface IDeliveryService {
    List<DeliveryItem> getDeliveryItems(int id);
}
