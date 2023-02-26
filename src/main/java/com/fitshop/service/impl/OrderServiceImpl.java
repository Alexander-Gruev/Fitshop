package com.fitshop.service.impl;

import com.fitshop.model.entity.OrderEntity;
import com.fitshop.model.entity.ProductEntity;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.service.OrderServiceModel;
import com.fitshop.model.view.OrderProfileViewModel;
import com.fitshop.model.view.OrderViewModel;
import com.fitshop.repository.OrderRepository;
import com.fitshop.repository.ProductRepository;
import com.fitshop.service.OrderService;
import com.fitshop.service.ProductService;
import com.fitshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public OrderEntity addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity orderEntity = this.modelMapper.map(orderServiceModel, OrderEntity.class);
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
                .map(o -> OrderViewModel.builder()
                        .productName(o.getProductName())
                        .clientFullName(o.getClientFullName())
                        .created(o.getCreated())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<OrderProfileViewModel> getUserOrdersByUsername(String username) {
        return this.orderRepository
                .findAllByClient_Username(username)
                .stream()
                .map(o -> OrderProfileViewModel.builder()
                        .address(o.getAddress())
                        .productName(o.getProductName())
                        .productBrandName(o.getProduct().getBrandName())
                        .created(o.getCreated())
                        .build())
                .collect(Collectors.toList());
    }

}
