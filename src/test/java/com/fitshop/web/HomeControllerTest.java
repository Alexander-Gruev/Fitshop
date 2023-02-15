package com.fitshop.web;

import com.fitshop.enums.UserRoleEnum;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.entity.UserRoleEntity;
import com.fitshop.repository.UserRepository;
import com.fitshop.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.PostConstruct;
import java.util.Set;

import static com.fitshop.GlobalTestConstants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    void setUp() {
        UserRoleEntity adminRole = new UserRoleEntity().setRoleEnum(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRoleEnum(UserRoleEnum.USER);
        userRoleRepository.save(userRole);
        userRoleRepository.save(adminRole);

        UserEntity testUser = new UserEntity()
                .setUsername(USERNAME)
                .setEmail(EMAIL)
                .setExperienceLevel(USER_EXPERIENCE)
                .setPassword(PASSWORD)
                .setRoles(Set.of(adminRole, userRole));

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = USERNAME)
    void testGetIndexShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cheapestProducts"))
                .andExpect(view().name(INDEX_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = USERNAME)
    void testGetAboutShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name(ABOUT_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = USERNAME)
    void testGetBeginnersShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/beginners"))
                .andExpect(status().isOk())
                .andExpect(view().name(BEGINNER_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = USERNAME)
    void testGetIntermediatesShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/intermediates"))
                .andExpect(status().isOk())
                .andExpect(view().name(INTERMEDIATE_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = USERNAME)
    void testGetAdvancedShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/advanced"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADVANCED_VIEW_NAME));
    }
}
