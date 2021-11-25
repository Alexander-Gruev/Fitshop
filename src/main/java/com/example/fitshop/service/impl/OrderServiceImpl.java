package com.example.fitshop.service.impl;

import com.example.fitshop.model.entity.OrderEntity;
import com.example.fitshop.model.entity.ProductEntity;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.service.OrderServiceModel;
import com.example.fitshop.model.view.OrderProfileViewModel;
import com.example.fitshop.model.view.OrderViewModel;
import com.example.fitshop.repository.OrderRepository;
import com.example.fitshop.repository.ProductRepository;
import com.example.fitshop.service.OrderService;
import com.example.fitshop.service.ProductService;
import com.example.fitshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ProductService productService, ModelMapper modelMapper, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public OrderEntity addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity orderEntity = this.modelMapper.map(orderServiceModel, OrderEntity.class);
        UserEntity client = this.userService.getByUsername(orderServiceModel.getClientUsername());
        ProductEntity product = this.productService.getByName(orderServiceModel.getProductName());

        orderEntity
                .setClient(client)
                .setProduct(product);

        this.orderRepository.save(orderEntity);
        this.productRepository.save(product.setOrdered(true));

        return orderEntity;
    }

    @Override
    public List<OrderViewModel> getAllOrders() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(o -> {
                            OrderViewModel orderViewModel = new OrderViewModel();
                            orderViewModel
                                    .setProductName(o.getProductName())
                                    .setClientFullName(o.getClientFullName())
                                    .setCreated(o.getCreated());
                            return orderViewModel;
                        }
                )
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<OrderProfileViewModel> getUserOrdersByUsername(String username) {
        return this.orderRepository
                .findAllByClient_Username(username)
                .stream()
                .map(o -> {
                    OrderProfileViewModel orderProfileViewModel = new OrderProfileViewModel();
                    orderProfileViewModel
                            .setAddress(o.getAddress())
                            .setProductName(o.getProductName())
                            .setProductBrandName(o.getProduct().getBrandName())
                            .setCreated(o.getCreated());

                    return orderProfileViewModel;
                })
                .collect(Collectors.toList());
    }

}
