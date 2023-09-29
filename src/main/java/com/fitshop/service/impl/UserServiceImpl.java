package com.fitshop.service.impl;

import com.fitshop.enums.UserExperienceEnum;
import com.fitshop.enums.UserRoleEnum;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.entity.UserRoleEntity;
import com.fitshop.model.service.UserPictureServiceModel;
import com.fitshop.model.service.UserRegisterServiceModel;
import com.fitshop.model.view.UserViewModel;
import com.fitshop.repository.UserRepository;
import com.fitshop.repository.UserRoleRepository;
import com.fitshop.service.CloudinaryService;
import com.fitshop.service.UserService;
import com.fitshop.web.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fitshop.model.mapper.UserMapper;

import java.io.IOException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final FitshopUserServiceImpl fitshopUserService;
    private final CloudinaryService cloudinaryService;
    private final UserMapper userMapper;

    @Override
    public void initUsersAndRoles() {
        initRoles();
        initUsers();
    }

    private void initUsers() {
        if (this.userRepository.count() == 0) {
            UserRoleEntity adminRole = this.userRoleRepository.findByRoleEnum(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = this.userRoleRepository.findByRoleEnum(UserRoleEnum.USER);

            UserEntity admin = UserEntity.builder()
                    .username("AlGruev")
                    .password(this.passwordEncoder.encode("secret"))
                    .email("realEmail@mail.com")
                    .experienceLevel(UserExperienceEnum.ADVANCED)
                    .roles(Set.of(adminRole, userRole))
                    .build();

            this.userRepository.save(admin);
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
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {
        UserRoleEntity userRole = this.userRoleRepository.findByRoleEnum(UserRoleEnum.USER);
        UserEntity userEntity = UserEntity.builder()
                .experienceLevel(userRegisterServiceModel.getExperienceLevel())
                .username(userRegisterServiceModel.getUsername())
                .password(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .email(userRegisterServiceModel.getEmail())
                .roles(Set.of(userRole))
                .build();

//        UserEntity userEntity = userMapper.mapFromRegisterServiceModelToEntity(userRegisterServiceModel, this.passwordEncoder);
//        userEntity.setRoles(Set.of(userRole));


        userEntity = this.userRepository.save(userEntity);

        UserDetails principal = this.fitshopUserService.loadUserByUsername(userEntity.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userEntity.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean isUsernameFree(String username) {
        return this.userRepository.findByUsername(username).isEmpty();
    }

    @Override
    public UserViewModel getViewModelByUsername(String username) {
        return this.userRepository
                .findByUsername(username)
                .map(this.userMapper::mapFromEntityToViewModel)
                .orElseThrow(() -> new ObjectNotFoundException("User with username " + username + " does not exist!"));
    }

    @Override
    public void updateWithPicture(UserPictureServiceModel userPictureServiceModel) throws IOException {
        UserEntity userEntity = this.userRepository
                .findByUsername(userPictureServiceModel.getUsername())
                .orElseThrow(() -> new ObjectNotFoundException("User with username " + userPictureServiceModel.getUsername() + " does not exist!"));

        String pictureUrl = this.cloudinaryService.uploadPicture(userPictureServiceModel.getPicture());
        userEntity.setPictureUrl(pictureUrl);
        this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity getByUsername(String username) {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User with username " + username + " does not exist!"));
    }

    @Override
    public boolean isEmailFree(String email) {
        return this.userRepository.findByEmail(email).isEmpty();
    }
}
