package com.example.fitshop.service.impl;

import com.example.fitshop.enums.UserExperienceEnum;
import com.example.fitshop.enums.UserRoleEnum;
import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.entity.UserRoleEntity;
import com.example.fitshop.model.service.UserPictureServiceModel;
import com.example.fitshop.model.service.UserRegisterServiceModel;
import com.example.fitshop.model.view.UserViewModel;
import com.example.fitshop.repository.UserRepository;
import com.example.fitshop.repository.UserRoleRepository;
import com.example.fitshop.service.CloudinaryService;
import com.example.fitshop.service.UserService;
import com.example.fitshop.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final FitshopUserServiceImpl fitshopUserService;
    private final CloudinaryService cloudinaryService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, FitshopUserServiceImpl fitshopUserService, CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.fitshopUserService = fitshopUserService;
        this.cloudinaryService = cloudinaryService;
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
                    .setExperienceLevel(UserExperienceEnum.ADVANCED)
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
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {
        UserRoleEntity userRole = this.userRoleRepository.findByRoleEnum(UserRoleEnum.USER);
        UserEntity userEntity = new UserEntity();
        userEntity.setExperienceLevel(userRegisterServiceModel.getExperience())
                .setUsername(userRegisterServiceModel.getUsername())
                .setPassword(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .setEmail(userRegisterServiceModel.getEmail())
                .setRoles(Set.of(userRole));

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
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
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
        return this.userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User with username " + username + " does not exist!"));
    }

    @Override
    public Long getIdByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User with username " + username + " does not exist!")).getId();
    }

}
