package com.fitshop.web;

import com.fitshop.GlobalTestConstants;
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
        UserRoleEntity adminRole = new UserRoleEntity(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity(UserRoleEnum.USER);
        userRoleRepository.save(userRole);
        userRoleRepository.save(adminRole);

        UserEntity testUser = UserEntity.builder()
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
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    void testGetIndexShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cheapestProducts"))
                .andExpect(view().name(GlobalTestConstants.INDEX_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    void testGetAboutShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name(GlobalTestConstants.ABOUT_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    void testGetBeginnersShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/beginners"))
                .andExpect(status().isOk())
                .andExpect(view().name(GlobalTestConstants.BEGINNER_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    void testGetIntermediatesShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/intermediates"))
                .andExpect(status().isOk())
                .andExpect(view().name(GlobalTestConstants.INTERMEDIATE_VIEW_NAME));
    }

    @Test
    @WithUserDetails(value = GlobalTestConstants.USERNAME)
    void testGetAdvancedShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/advanced"))
                .andExpect(status().isOk())
                .andExpect(view().name(GlobalTestConstants.ADVANCED_VIEW_NAME));
    }
}
