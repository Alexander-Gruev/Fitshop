package com.example.fitshop.web;

import com.example.fitshop.enums.UserExperienceEnum;
import com.example.fitshop.enums.UserRoleEnum;
import com.example.fitshop.model.custom.FitshopUser;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.entity.UserRoleEntity;
import com.example.fitshop.repository.UserRepository;
import com.example.fitshop.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    private static final String NEW_PRODUCT_NAME = "productName";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity testUser;

    @BeforeEach
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

        Set<SimpleGrantedAuthority> authorities = testUser
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleEnum().name()))
                .collect(Collectors.toSet());

        FitshopUser fitshopUser = new FitshopUser("username", "password", authorities, "ADVANCED");
    }

    @Test
    @WithMockUser(value = "username") //@WithUserDetails(value = "username")
    void testGetNewProductShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/new/" + NEW_PRODUCT_NAME))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("productName"))
                .andExpect(view().name("order-new"));
    }

}