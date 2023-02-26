package com.fitshop.web;

import com.fitshop.GlobalTestConstants;
import com.fitshop.enums.UserRoleEnum;
import com.fitshop.model.entity.OrderEntity;
import com.fitshop.model.entity.ProductEntity;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.entity.UserRoleEntity;
import com.fitshop.repository.OrderRepository;
import com.fitshop.repository.ProductRepository;
import com.fitshop.repository.UserRepository;
import com.fitshop.repository.UserRoleRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        UserRoleEntity adminRole = new UserRoleEntity(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity(UserRoleEnum.USER);
        userRoleRepository.save(userRole);
        userRoleRepository.save(adminRole);

        testUser = UserEntity.builder()
                .username(GlobalTestConstants.USERNAME)
                .email(GlobalTestConstants.EMAIL)
                .experienceLevel(GlobalTestConstants.USER_EXPERIENCE)
                .password(GlobalTestConstants.PASSWORD)
                .roles(Set.of(adminRole, userRole))
                .build();

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
                .andExpect(jsonPath("$.[0].productName", is(GlobalTestConstants.PRODUCT_NAME)))
                .andExpect(jsonPath("$.[0].clientFullName", is(GlobalTestConstants.ORDER_CLIENT_FULL_NAME)));
    }

    @Test
    @WithMockUser(value = GlobalTestConstants.USERNAME)
    void testGetUserOrdersShouldReturnCorrectOrders() throws Exception {
        OrderEntity order = initOrderWithProduct();
        testUser.setOrders(Set.of(order));
        testUser = userRepository.save(testUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].productName", is(GlobalTestConstants.PRODUCT_NAME)))
                .andExpect(jsonPath("$.[0].productBrandName", is(GlobalTestConstants.PRODUCT_BRAND_NAME)))
                .andExpect(jsonPath("$.[0].address", is(GlobalTestConstants.ORDER_ADDRESS)));
    }

    private OrderEntity initOrderWithProduct() {
        ProductEntity product = ProductEntity.builder()
                .brandName(GlobalTestConstants.PRODUCT_BRAND_NAME)
                .name(GlobalTestConstants.PRODUCT_NAME)
                .description(GlobalTestConstants.PRODUCT_DESCRIPTION)
                .imageUrl(GlobalTestConstants.PRODUCT_IMG_URL)
                .price(GlobalTestConstants.PRODUCT_PRICE)
                .category(GlobalTestConstants.PRODUCT_CATEGORY)
                .build();

        product = productRepository.save(product);

        OrderEntity order = OrderEntity.builder()
                .country(GlobalTestConstants.ORDER_COUNTRY)
                .client(testUser)
                .product(product)
                .paymentMethod(GlobalTestConstants.ORDER_PAYMENT_METHOD)
                .phoneNumber(GlobalTestConstants.ORDER_PHONE_NUMBER)
                .postcode(GlobalTestConstants.ORDER_POSTCODE)
                .address(GlobalTestConstants.ORDER_ADDRESS)
                .productName(GlobalTestConstants.PRODUCT_NAME)
                .clientFullName(GlobalTestConstants.ORDER_CLIENT_FULL_NAME)
                .created(Instant.parse(GlobalTestConstants.CREATED_STRING))
                .build();

        order = orderRepository.save(order);
        return order;
    }

}