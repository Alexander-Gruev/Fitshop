package com.example.fitshop.web;

import com.example.fitshop.enums.UserRoleEnum;
import com.example.fitshop.model.entity.ProductEntity;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.entity.UserRoleEntity;
import com.example.fitshop.repository.OrderRepository;
import com.example.fitshop.repository.ProductRepository;
import com.example.fitshop.repository.UserRepository;
import com.example.fitshop.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static com.example.fitshop.GlobalTestConstants.*;

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
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = USERNAME)
    void testGetNewOrderShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/new/" + PRODUCT_NAME))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("productName"))
                .andExpect(view().name(NEW_ORDER_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = USERNAME)
    void testPostNewOrderShouldCreateNewProductWithValidFields() throws Exception {
        initProduct();
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/new/" + PRODUCT_NAME).
                        param("country", ORDER_COUNTRY).
                        param("clientFullName", ORDER_CLIENT_FULL_NAME).
                        param("postcode", String.valueOf(ORDER_POSTCODE)).
                        param("address", ORDER_ADDRESS).
                        param("email", ORDER_EMAIL).
                        param("phoneNumber", String.valueOf(ORDER_PHONE_NUMBER)).
                        param("paymentMethod", ORDER_PAYMENT_METHOD).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        assertEquals(1, orderRepository.count());
    }

    @Test
    @WithUserDetails(value = USERNAME)
    void testPostNewOrderShouldNotCreateNewProductWithInvalidFields() throws Exception {
        initProduct();
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/new/" + PRODUCT_NAME).
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
        ProductEntity product = new ProductEntity()
                .setBrandName(PRODUCT_BRAND_NAME)
                .setName(PRODUCT_NAME)
                .setDescription(PRODUCT_DESCRIPTION)
                .setImageUrl(PRODUCT_IMG_URL)
                .setPrice(PRODUCT_PRICE)
                .setCategory(PRODUCT_CATEGORY);

        product = productRepository.save(product);
    }




}