package com.fitshop.service;

import com.fitshop.model.entity.OrderEntity;
import com.fitshop.model.service.OrderServiceModel;
import com.fitshop.model.view.OrderProfileViewModel;
import com.fitshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {

    OrderEntity addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> getAllOrders();

    List<OrderProfileViewModel> getUserOrdersByUsername(String username);

}
