package com.fitshop.service.impl;

import com.fitshop.GlobalTestConstants;
import com.fitshop.enums.UserRoleEnum;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.entity.UserRoleEntity;
import com.fitshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
        UserRoleEntity adminRole = new UserRoleEntity(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity(UserRoleEnum.USER);
        user = UserEntity.builder()
                .username(GlobalTestConstants.USERNAME)
                .password(GlobalTestConstants.PASSWORD)
                .experienceLevel(GlobalTestConstants.USER_EXPERIENCE)
                .email(GlobalTestConstants.EMAIL)
                .roles(Set.of(adminRole, userRole))
                .build();
    }

    @Test
    void testLoadUserByUsernameShouldThrow() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername(GlobalTestConstants.NON_EXISTENT_USERNAME)
        );
    }

    @Test
    void testLoadUserByUsernameShouldReturnCorrectUserDetails() {
        Mockito.when(mockUserRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        UserDetails actual = serviceToTest.loadUserByUsername(GlobalTestConstants.USERNAME);

        String actualRoles = actual
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(user.getUsername(), actual.getUsername());
        Assertions.assertEquals(GlobalTestConstants.EXPECTED_ROLES_STRING, actualRoles);
    }

}
