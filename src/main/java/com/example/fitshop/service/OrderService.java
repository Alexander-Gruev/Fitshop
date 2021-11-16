package com.example.fitshop.service;

import com.example.fitshop.model.service.OrderServiceModel;

public interface OrderService {

    void addOrder(OrderServiceModel orderServiceModel);

    String getProductNameById(Long id);

}
