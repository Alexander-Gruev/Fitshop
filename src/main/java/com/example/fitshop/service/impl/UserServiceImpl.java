package com.example.fitshop.service.impl;

import com.example.fitshop.enums.UserRoleEnum;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.entity.UserRoleEntity;
import com.example.fitshop.repository.UserRepository;
import com.example.fitshop.repository.UserRoleRepository;
import com.example.fitshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initUsersAndRoles() {
        initRoles();
        initUsers();
    }

    private void initUsers() {
        if (this.userRepository.count() == 0) {
            UserEntity userEntity = new UserEntity();
            UserRoleEntity adminRole = this.userRoleRepository.findByRoleEnum(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = this.userRoleRepository.findByRoleEnum(UserRoleEnum.USER);

            userEntity
                    .setUsername("AlGruev")
                    .setPassword(this.passwordEncoder.encode("secret"))
                    .setEmail("realEmail@mail.com")
                    .setRoles(Set.of(adminRole, userRole));

            this.userRepository.save(userEntity);
        }
    }

    private void initRoles() {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity();
            admin.setRoleEnum(UserRoleEnum.ADMIN);

            UserRoleEntity user = new UserRoleEntity();
            user.setRoleEnum(UserRoleEnum.USER);

            this.userRoleRepository.saveAll(Set.of(admin, user));
        }
    }

    @Override
    public void registerAndLoginUser() {
    }
}
