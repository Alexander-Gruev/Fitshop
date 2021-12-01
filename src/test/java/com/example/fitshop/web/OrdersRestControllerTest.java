package com.example.fitshop.web;

import com.example.fitshop.enums.UserRoleEnum;
import com.example.fitshop.model.entity.OrderEntity;
import com.example.fitshop.model.entity.ProductEntity;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.entity.UserRoleEntity;
import com.example.fitshop.repository.OrderRepository;
import com.example.fitshop.repository.ProductRepository;
import com.example.fitshop.repository.UserRepository;
import com.example.fitshop.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.example.fitshop.GlobalTestConstants.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrdersRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private UserEntity testUser;

    @BeforeEach()
    void setUp() {
        UserRoleEntity adminRole = new UserRoleEntity().setRoleEnum(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRoleEnum(UserRoleEnum.USER);
        userRoleRepository.save(userRole);
        userRoleRepository.save(adminRole);

        testUser = new UserEntity()
                .setUsername(USERNAME)
                .setEmail(EMAIL)
                .setExperienceLevel(USER_EXPERIENCE)
                .setPassword(PASSWORD)
                .setRoles(Set.of(adminRole, userRole));

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }


    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void testGetAllOrdersShouldReturnCorrectOrders() throws Exception {
        initOrderWithProduct();
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].productName", is(PRODUCT_NAME)))
                .andExpect(jsonPath("$.[0].clientFullName", is(ORDER_CLIENT_FULL_NAME)));
    }

    @Test
    @WithMockUser(value = USERNAME)
    void testGetUserOrdersShouldReturnCorrectOrders() throws Exception {
        OrderEntity order = initOrderWithProduct();
        testUser.setOrders(Set.of(order));
        testUser = userRepository.save(testUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].productName", is(PRODUCT_NAME)))
                .andExpect(jsonPath("$.[0].productBrandName", is(PRODUCT_BRAND_NAME)))
                .andExpect(jsonPath("$.[0].address", is(ORDER_ADDRESS)));
    }

    private OrderEntity initOrderWithProduct() {
        ProductEntity product = new ProductEntity()
                .setBrandName(PRODUCT_BRAND_NAME)
                .setName(PRODUCT_NAME)
                .setDescription(PRODUCT_DESCRIPTION)
                .setImageUrl(PRODUCT_IMG_URL)
                .setPrice(PRODUCT_PRICE)
                .setCategory(PRODUCT_CATEGORY);

        product = productRepository.save(product);

        OrderEntity order = new OrderEntity()
                .setCountry(ORDER_COUNTRY)
                .setClient(testUser)
                .setProduct(product)
                .setPaymentMethod(ORDER_PAYMENT_METHOD)
                .setPhoneNumber(ORDER_PHONE_NUMBER)
                .setPostcode(ORDER_POSTCODE)
                .setAddress(ORDER_ADDRESS)
                .setProductName(PRODUCT_NAME)
                .setClientFullName(ORDER_CLIENT_FULL_NAME)
                .setCreated(Instant.parse(CREATED_STRING));

        order = orderRepository.save(order);
        return order;
    }

}