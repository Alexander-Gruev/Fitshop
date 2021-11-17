package com.example.fitshop.service;

import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.model.service.UserPictureServiceModel;
import com.example.fitshop.model.service.UserRegisterServiceModel;
import com.example.fitshop.model.view.UserViewModel;

import java.io.IOException;

public interface UserService {

    void initUsersAndRoles();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean isUsernameFree(String username);

    UserViewModel getViewModelByUsername(String username);

    void updateWithPicture(UserPictureServiceModel userPictureServiceModel) throws IOException;

    UserEntity getByUsername(String username);

    Long getIdByUsername(String username);

}
