package com.codegym.project_team2.service;

import com.codegym.project_team2.model.DeliveryItem;
import com.codegym.project_team2.repository.DeliveryRepository;
import com.codegym.project_team2.repository.IDeliveryRepository;

import java.util.List;

public class DeliveryService implements IDeliveryService {
    private IDeliveryRepository deliveryRepository = new DeliveryRepository();
    @Override
    public List<DeliveryItem> getDeliveryItems(int id) {
        return deliveryRepository.getDeliveryItems(id);
    }
}
