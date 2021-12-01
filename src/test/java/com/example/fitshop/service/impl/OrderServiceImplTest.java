package com.example.fitshop.service.impl;

import com.example.fitshop.model.entity.OrderEntity;
import com.example.fitshop.model.entity.ProductEntity;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.service.OrderServiceModel;
import com.example.fitshop.model.view.OrderProfileViewModel;
import com.example.fitshop.model.view.OrderViewModel;
import com.example.fitshop.repository.OrderRepository;
import com.example.fitshop.repository.ProductRepository;
import com.example.fitshop.service.ProductService;
import com.example.fitshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static com.example.fitshop.GlobalTestConstants.*;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        UserEntity client = new UserEntity().setUsername(USERNAME);
        ProductEntity product = new ProductEntity().setName(PRODUCT_NAME);

        OrderServiceModel orderServiceModel = new OrderServiceModel();
        orderServiceModel
                .setProductName(product.getName())
                .setClientUsername(client.getUsername());

        Mockito.when(userService.getByUsername(orderServiceModel.getClientUsername())).thenReturn(client);
        Mockito.when(productService.getByName(orderServiceModel.getProductName())).thenReturn(product);

        OrderEntity expectedOrder = new OrderEntity().setProduct(product).setClient(client);
        OrderEntity actualOrder = serviceToTest.addOrder(orderServiceModel);

        assertTrue(new ReflectionEquals(expectedOrder.getProduct()).matches(actualOrder.getProduct()));
        assertTrue(new ReflectionEquals(expectedOrder.getClient()).matches(actualOrder.getClient()));
    }

    @Test
    void testGetAllOrdersShouldReturnCorrectListOfOrderViewModels() {
        OrderEntity order = new OrderEntity()
                .setProductName(PRODUCT_NAME)
                .setClientFullName(CLIENT_FULL_NAME)
                .setCreated(Instant.parse(CREATED_STRING));

        Mockito.when(orderRepository.findAll()).thenReturn(List.of(order));

        OrderViewModel expected = new OrderViewModel()
                .setProductName(PRODUCT_NAME)
                .setClientFullName(CLIENT_FULL_NAME)
                .setCreated(Instant.parse(CREATED_STRING));

        assertTrue(new ReflectionEquals(expected).matches(serviceToTest.getAllOrders().get(0)));
    }

    @Test
    void testGetUserOrdersByUsernameShouldReturnCorrectListOfOrderProfileViewModels() {
        OrderEntity order = new OrderEntity()
                .setAddress(ORDER_ADDRESS)
                .setProductName(PRODUCT_NAME)
                .setProduct(new ProductEntity().setBrandName(PRODUCT_BRAND_NAME))
                .setCreated(Instant.parse(CREATED_STRING));

        Mockito.when(orderRepository.findAllByClient_Username(USERNAME)).thenReturn(List.of(order));

        OrderProfileViewModel expected = new OrderProfileViewModel()
                .setAddress(ORDER_ADDRESS)
                .setProductName(PRODUCT_NAME)
                .setProductBrandName(PRODUCT_BRAND_NAME)
                .setCreated(Instant.parse(CREATED_STRING));

        assertTrue(new ReflectionEquals(expected).matches(serviceToTest.getUserOrdersByUsername(USERNAME).get(0)));
    }

}