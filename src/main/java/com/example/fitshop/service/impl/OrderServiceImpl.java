package com.example.fitshop.service.impl;

import com.example.fitshop.model.entity.OrderEntity;
import com.example.fitshop.model.entity.ProductEntity;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.service.OrderServiceModel;
import com.example.fitshop.repository.OrderRepository;
import com.example.fitshop.service.OrderService;
import com.example.fitshop.service.ProductService;
import com.example.fitshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ProductService productService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity orderEntity = this.modelMapper.map(orderServiceModel, OrderEntity.class);
        UserEntity client = this.userService.getByUsername(orderServiceModel.getClientUsername());
        ProductEntity product = this.productService.getByName(orderServiceModel.getProductName());

        orderEntity
                .setClient(client)
                .setProduct(product);

        this.orderRepository.save(orderEntity);
    }

    @Override
    public String getProductNameById(Long id) {
        return this.orderRepository.getNameById(id);
    }


}
