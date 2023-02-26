package com.fitshop.service.impl;

import com.fitshop.GlobalTestConstants;
import com.fitshop.model.entity.OrderEntity;
import com.fitshop.model.entity.ProductEntity;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.service.OrderServiceModel;
import com.fitshop.model.view.OrderProfileViewModel;
import com.fitshop.model.view.OrderViewModel;
import com.fitshop.repository.OrderRepository;
import com.fitshop.repository.ProductRepository;
import com.fitshop.service.ProductService;
import com.fitshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Mock
    private ProductRepository productRepository;

    private OrderServiceImpl serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new OrderServiceImpl(orderRepository, userService, productService, modelMapper, productRepository);
    }

    @Test
    void testAddOrderReturnsCorrectOrderEntity() {
        UserEntity client = UserEntity.builder().username(GlobalTestConstants.USERNAME).build();
        ProductEntity product = ProductEntity.builder().name(GlobalTestConstants.PRODUCT_NAME).build();

        OrderServiceModel orderServiceModel = OrderServiceModel.builder()
                .productName(product.getName())
                .clientUsername(client.getUsername())
                .build();

        Mockito.when(userService.getByUsername(orderServiceModel.getClientUsername())).thenReturn(client);
        Mockito.when(productService.getByName(orderServiceModel.getProductName())).thenReturn(product);

        OrderEntity expectedOrder = OrderEntity.builder().product(product).client(client).build();
        OrderEntity actualOrder = serviceToTest.addOrder(orderServiceModel);

        assertTrue(new ReflectionEquals(expectedOrder.getProduct()).matches(actualOrder.getProduct()));
        assertTrue(new ReflectionEquals(expectedOrder.getClient()).matches(actualOrder.getClient()));
    }

    @Test
    void testGetAllOrdersShouldReturnCorrectListOfOrderViewModels() {
        OrderEntity order = OrderEntity.builder()
                .productName(GlobalTestConstants.PRODUCT_NAME)
                .clientFullName(GlobalTestConstants.CLIENT_FULL_NAME)
                .created(Instant.parse(GlobalTestConstants.CREATED_STRING))
                .build();

        Mockito.when(orderRepository.findAll()).thenReturn(List.of(order));

        OrderViewModel expected = OrderViewModel.builder()
                .productName(GlobalTestConstants.PRODUCT_NAME)
                .clientFullName(GlobalTestConstants.CLIENT_FULL_NAME)
                .created(Instant.parse(GlobalTestConstants.CREATED_STRING))
                .build();

        assertTrue(new ReflectionEquals(expected).matches(serviceToTest.getAllOrders().get(0)));
    }

    @Test
    void testGetUserOrdersByUsernameShouldReturnCorrectListOfOrderProfileViewModels() {
        OrderEntity order = OrderEntity.builder()
                .address(GlobalTestConstants.ORDER_ADDRESS)
                .productName(GlobalTestConstants.PRODUCT_NAME)
                .product(ProductEntity.builder().brandName(GlobalTestConstants.PRODUCT_BRAND_NAME).build())
                .created(Instant.parse(GlobalTestConstants.CREATED_STRING))
                .build();

        Mockito.when(orderRepository.findAllByClient_Username(GlobalTestConstants.USERNAME)).thenReturn(List.of(order));

        OrderProfileViewModel expected = OrderProfileViewModel.builder()
                .address(GlobalTestConstants.ORDER_ADDRESS)
                .productName(GlobalTestConstants.PRODUCT_NAME)
                .productBrandName(GlobalTestConstants.PRODUCT_BRAND_NAME)
                .created(Instant.parse(GlobalTestConstants.CREATED_STRING))
                .build();

        assertTrue(new ReflectionEquals(expected).matches(serviceToTest.getUserOrdersByUsername(GlobalTestConstants.USERNAME).get(0)));
    }

}