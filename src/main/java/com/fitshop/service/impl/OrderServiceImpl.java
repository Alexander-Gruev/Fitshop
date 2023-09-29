package com.fitshop.service.impl;

import com.fitshop.model.entity.OrderEntity;
import com.fitshop.model.entity.ProductEntity;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.mapper.OrderMapper;
import com.fitshop.model.service.OrderServiceModel;
import com.fitshop.model.view.OrderProfileViewModel;
import com.fitshop.model.view.OrderViewModel;
import com.fitshop.repository.OrderRepository;
import com.fitshop.repository.ProductRepository;
import com.fitshop.service.OrderService;
import com.fitshop.service.ProductService;
import com.fitshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderEntity addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity orderEntity = this.orderMapper.mapFromServiceModelToEntity(orderServiceModel);
        UserEntity client = this.userService.getByUsername(orderServiceModel.getClientUsername());
        ProductEntity product = this.productService.getByName(orderServiceModel.getProductName());

        orderEntity.setClient(client);
        orderEntity.setProduct(product);

        this.orderRepository.save(orderEntity);
        product.setOrdered(true);
        this.productRepository.save(product);

        return orderEntity;
    }

    @Override
    public List<OrderViewModel> getAllOrders() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(orderMapper::mapFromEntityToViewModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<OrderProfileViewModel> getUserOrdersByUsername(String username) {
        return this.orderRepository
                .findAllByClient_Username(username)
                .stream()
                .map(this.orderMapper::mapFromEntityToProfileViewModel)
                .collect(Collectors.toList());
    }

}
