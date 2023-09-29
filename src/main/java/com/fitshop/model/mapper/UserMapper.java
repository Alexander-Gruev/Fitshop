package com.fitshop.model.mapper;

import com.fitshop.model.binding.UserRegisterBindingModel;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.service.UserRegisterServiceModel;
import com.fitshop.model.view.UserViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", imports = {PasswordEncoder.class})
public interface UserMapper {

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(registerServiceModel.getPassword()))")
    UserEntity mapFromRegisterServiceModelToEntity(UserRegisterServiceModel registerServiceModel, PasswordEncoder passwordEncoder);

    UserViewModel mapFromEntityToViewModel(UserEntity entity);

    UserRegisterServiceModel mapFromRegisterBindingModelToRegisterServiceModel(UserRegisterBindingModel registerBindingModel);
}
