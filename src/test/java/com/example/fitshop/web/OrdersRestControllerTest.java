package com.example.fitshop.web;

import com.example.fitshop.enums.ProductCategoryEnum;
import com.example.fitshop.enums.UserExperienceEnum;
import com.example.fitshop.enums.UserRoleEnum;
import com.example.fitshop.model.custom.FitshopUser;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .setUsername("username")
                .setEmail("email")
                .setExperienceLevel(UserExperienceEnum.ADVANCED)
                .setPassword("password")
                .setRoles(Set.of(adminRole, userRole));

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void testGetAllOrdersShouldReturnCorrectOrders() throws Exception {
        initOrderWithProduct();
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].productName", is("productName")))
                .andExpect(jsonPath("$.[0].clientFullName", is("clientFullName")));
    }

    @Test
    @WithMockUser("username")
    void testGetUserOrdersShouldReturnCorrectOrders() throws Exception {
        OrderEntity order = initOrderWithProduct();
        testUser.setOrders(Set.of(order));
        testUser = userRepository.save(testUser);

        Set<SimpleGrantedAuthority> authorities = testUser
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleEnum().name()))
                .collect(Collectors.toSet());

        FitshopUser fitshopUser = new FitshopUser("username", "password", authorities, "ADVANCED");

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/user", fitshopUser))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].productName", is("productName")))
                .andExpect(jsonPath("$.[0].productBrandName", is("brandName")))
                .andExpect(jsonPath("$.[0].address", is("address")));
    }

    private OrderEntity initOrderWithProduct() {
        ProductEntity product = new ProductEntity()
                .setBrandName("brandName")
                .setName("productName")
                .setDescription("desc")
                .setImageUrl("imgUrl")
                .setPrice(BigDecimal.TEN)
                .setCategory(ProductCategoryEnum.WEIGHTS);

        product = productRepository.save(product);

        OrderEntity order = new OrderEntity()
                .setCountry("Germany")
                .setClient(testUser)
                .setProduct(product)
                .setPaymentMethod("Credit card")
                .setPhoneNumber("123456789")
                .setPostcode("1000")
                .setAddress("address")
                .setProductName("productName")
                .setClientFullName("clientFullName")
                .setCreated(Instant.parse("2018-11-30T18:35:24.00Z"));

        order = orderRepository.save(order);
        return order;
    }


}