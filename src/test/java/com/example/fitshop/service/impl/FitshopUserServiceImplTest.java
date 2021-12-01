package com.example.fitshop.service.impl;

import com.example.fitshop.enums.UserRoleEnum;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.entity.UserRoleEntity;
import com.example.fitshop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static com.example.fitshop.GlobalTestConstants.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class FitshopUserServiceImplTest {

    private FitshopUserServiceImpl serviceToTest;
    private UserEntity user;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new FitshopUserServiceImpl(mockUserRepository);
        UserRoleEntity adminRole = new UserRoleEntity().setRoleEnum(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRoleEnum(UserRoleEnum.USER);
        user = new UserEntity()
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setExperienceLevel(USER_EXPERIENCE)
                .setEmail(EMAIL)
                .setRoles(Set.of(adminRole, userRole));
    }

    @Test
    void testLoadUserByUsernameShouldThrow() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername(NON_EXISTENT_USERNAME)
        );
    }

    @Test
    void testLoadUserByUsernameShouldReturnCorrectUserDetails() {
        Mockito.when(mockUserRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        UserDetails actual = serviceToTest.loadUserByUsername(USERNAME);

        String actualRoles = actual
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(user.getUsername(), actual.getUsername());
        Assertions.assertEquals(EXPECTED_ROLES_STRING, actualRoles);
    }

}
