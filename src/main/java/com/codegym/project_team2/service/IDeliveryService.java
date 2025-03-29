package com.codegym.project_team2.service;

import com.codegym.project_team2.model.DeliveryItem;
import com.codegym.project_team2.model.DishOption;

import java.util.List;

public interface IDeliveryService {
    List<DeliveryItem> getDeliveryItems(int id);
    DeliveryItem getDeliveryItemById(int id);
    List<DishOption> getDishesWithOption(int id);
    void changeDeliverystatus(int id, String status);
    int showNumbersOfOrdersHistory(int id);
    int shippersOrdersCount();
}
