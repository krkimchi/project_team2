package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.DeliveryItem;

import java.util.List;

public interface IDeliveryRepository {
    List<DeliveryItem> getDeliveryItems(int id);
}
