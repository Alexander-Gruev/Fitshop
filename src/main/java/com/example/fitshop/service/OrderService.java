package com.example.fitshop.service;

import com.example.fitshop.model.service.OrderServiceModel;
import com.example.fitshop.model.view.OrderProfileViewModel;
import com.example.fitshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {

    void addOrder(OrderServiceModel orderServiceModel);

    String getProductNameById(Long id);

    List<OrderViewModel> getAllOrders();

    List<OrderProfileViewModel> getUserOrdersByUsername(String username);

}
