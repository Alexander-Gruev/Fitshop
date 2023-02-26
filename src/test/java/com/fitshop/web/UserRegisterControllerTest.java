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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegisterControllerTest {

    private static final String REGISTER_VIEW_NAME = "auth-register";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @Test
    void testGetRegisterShouldReturnCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTER_VIEW_NAME))
                .andExpect(model().attributeExists("experienceLevels"));
    }

    @Test
    void testPostRegisterShouldRegisterUserWithCorrectFields() throws Exception {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity userRole = new UserRoleEntity(UserRoleEnum.USER);
            userRoleRepository.save(userRole);
        }

        mockMvc.perform(post("/users/register").
                        param("username", GlobalTestConstants.USERNAME).
                        param("email", GlobalTestConstants.EMAIL).
                        param("experienceLevel", GlobalTestConstants.USER_LEVEL_STRING).
                        param("password", GlobalTestConstants.PASSWORD).
                        param("confirmPassword", GlobalTestConstants.PASSWORD).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Optional<UserEntity> newUserOpt = userRepository.findByUsername(GlobalTestConstants.USERNAME);

        assertTrue(newUserOpt.isPresent());

        UserEntity newUser = newUserOpt.get();
        assertEquals(newUser.getExperienceLevel().name(), GlobalTestConstants.USER_LEVEL_STRING);
        assertEquals(newUser.getUsername(), GlobalTestConstants.USERNAME);
        assertEquals(newUser.getEmail(), GlobalTestConstants.EMAIL);
    }

    @Test
    void testPostRegisterShouldFailInCaseOfInvalidFields() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("username", "").
                        param("email", GlobalTestConstants.EMAIL).
                        param("experienceLevel", GlobalTestConstants.USER_LEVEL_STRING).
                        param("password", GlobalTestConstants.PASSWORD).
                        param("confirmPassword", GlobalTestConstants.PASSWORD).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Optional<UserEntity> newUserOpt = userRepository.findByUsername("");
        assertTrue(newUserOpt.isEmpty());
    }
}