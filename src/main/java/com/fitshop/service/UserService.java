package com.fitshop.service;

import com.fitshop.model.entity.UserEntity;
import com.fitshop.model.service.UserPictureServiceModel;
import com.fitshop.model.service.UserRegisterServiceModel;
import com.fitshop.model.view.UserViewModel;

import java.io.IOException;

public interface UserService {

    void initUsersAndRoles();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean isUsernameFree(String username);

    UserViewModel getViewModelByUsername(String username);

    void updateWithPicture(UserPictureServiceModel userPictureServiceModel) throws IOException;

    UserEntity getByUsername(String username);

    boolean isEmailFree(String email);

}
