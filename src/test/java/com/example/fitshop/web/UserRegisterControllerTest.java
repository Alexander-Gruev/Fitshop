package com.example.fitshop.web;

import com.example.fitshop.enums.UserRoleEnum;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.entity.UserRoleEntity;
import com.example.fitshop.repository.UserRepository;
import com.example.fitshop.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class UserRegisterControllerTest {

    private static final String TEST_USER_USERNAME = "pesho0123";
    private static final String TEST_USER_EMAIL = "pesho@abv.bg";
    private static final String TEST_USER_LEVEL = "ADVANCED";
    private static final String TEST_USER_PASS = "12345";

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
                .andExpect(view().name("auth-register"))
                .andExpect(model().attributeExists("experienceLevels"));
    }

    @Test
    void testPostRegisterShouldRegisterUserWithCorrectFields() throws Exception {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity userRole = new UserRoleEntity().setRoleEnum(UserRoleEnum.USER);
            userRoleRepository.save(userRole);
        }

        mockMvc.perform(post("/users/register").
                        param("username", TEST_USER_USERNAME).
                        param("email", TEST_USER_EMAIL).
                        param("experienceLevel", TEST_USER_LEVEL).
                        param("password", TEST_USER_PASS).
                        param("confirmPassword", TEST_USER_PASS).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Optional<UserEntity> newUserOpt = userRepository.findByUsername(TEST_USER_USERNAME);

        assertTrue(newUserOpt.isPresent());

        UserEntity newUser = newUserOpt.get();
        assertEquals(newUser.getExperienceLevel().name(), TEST_USER_LEVEL);
        assertEquals(newUser.getUsername(), TEST_USER_USERNAME);
        assertEquals(newUser.getEmail(), TEST_USER_EMAIL);
    }

    @Test
    void testPostRegisterShouldFailInCaseOfInvalidFields() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("username", "").
                        param("email", TEST_USER_EMAIL).
                        param("experienceLevel", TEST_USER_LEVEL).
                        param("password", TEST_USER_PASS).
                        param("confirmPassword", TEST_USER_PASS).
                        with(csrf()).
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Optional<UserEntity> newUserOpt = userRepository.findByUsername("");
        assertTrue(newUserOpt.isEmpty());
    }
}