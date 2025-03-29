package com.codegym.project_team2.service;

import com.codegym.project_team2.model.DeliveryItem;
import com.codegym.project_team2.model.DishOption;
import com.codegym.project_team2.repository.DeliveryRepository;
import com.codegym.project_team2.repository.IDeliveryRepository;

import java.util.List;

public class DeliveryService implements IDeliveryService {
    private final IDeliveryRepository deliveryRepository = new DeliveryRepository();
    @Override
    public List<DeliveryItem> getDeliveryItems(int id) {
        return deliveryRepository.getDeliveryItems(id);
    }

    @Override
    public DeliveryItem getDeliveryItemById(int id) {
        return deliveryRepository.getDeliveryItemById(id);
    }

    @Override
    public List<DishOption> getDishesWithOption(int id) {
        return deliveryRepository.getDishesWithOption(id);
    }

    @Override
    public void changeDeliverystatus(int id, String status) {
        deliveryRepository.changeDeliveryStatus(id, status);
    }

    @Override
    public int showNumbersOfOrdersHistory(int id) {
        return deliveryRepository.showNumbersOfOrdersHistory(id);
    }


    @Override
    public int shippersOrdersCount() {
        return deliveryRepository.shippersOrdersCount();
    }
}
