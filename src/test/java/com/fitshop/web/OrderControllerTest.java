package com.fitshop.web;

import com.fitshop.GlobalTestConstants;
import com.fitshop.enums.UserRoleEnum;
import com.fitshop.model.entity.ProductEntity;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.entity.UserRoleEntity;
import com.fitshop.repository.OrderRepository;
import com.fitshop.repository.ProductRepository;
import com.fitshop.repository.UserRepository;
import com.fitshop.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.PostConstruct;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private UserEntity testUser;

    @PostConstruct
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
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    void testGetNewOrderShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/new/" + GlobalTestConstants.PRODUCT_NAME))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("productName"))
                .andExpect(view().name(GlobalTestConstants.NEW_ORDER_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    void testPostNewOrderShouldCreateNewProductWithValidFields() throws Exception {
        initProduct();
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/new/" + GlobalTestConstants.PRODUCT_NAME).
                        param("country", GlobalTestConstants.ORDER_COUNTRY).
                        param("clientFullName", GlobalTestConstants.ORDER_CLIENT_FULL_NAME).
                        param("postcode", String.valueOf(GlobalTestConstants.ORDER_POSTCODE)).
                        param("address", GlobalTestConstants.ORDER_ADDRESS).
                        param("email", GlobalTestConstants.ORDER_EMAIL).
                        param("phoneNumber", String.valueOf(GlobalTestConstants.ORDER_PHONE_NUMBER)).
                        param("paymentMethod", GlobalTestConstants.ORDER_PAYMENT_METHOD).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        assertEquals(1, orderRepository.count());
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    void testPostNewOrderShouldNotCreateNewProductWithInvalidFields() throws Exception {
        initProduct();
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/new/" + GlobalTestConstants.PRODUCT_NAME).
                        param("country", "1").
                        param("clientFullName", "1").
                        param("postcode", "1").
                        param("address", "1").
                        param("email", "1").
                        param("phoneNumber", "1").
                        param("paymentMethod", "1").
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        assertEquals(0, orderRepository.count());
    }

    void initProduct() {
        ProductEntity product = ProductEntity.builder()
                .brandName(GlobalTestConstants.PRODUCT_BRAND_NAME)
                .name(GlobalTestConstants.PRODUCT_NAME)
                .description(GlobalTestConstants.PRODUCT_DESCRIPTION)
                .imageUrl(GlobalTestConstants.PRODUCT_IMG_URL)
                .price(GlobalTestConstants.PRODUCT_PRICE)
                .category(GlobalTestConstants.PRODUCT_CATEGORY)
                .build();

        product = productRepository.save(product);
    }
}