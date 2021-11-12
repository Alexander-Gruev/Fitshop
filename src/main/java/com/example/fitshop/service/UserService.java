package com.example.fitshop.service;

import com.example.fitshop.model.service.UserRegisterServiceModel;
import com.example.fitshop.model.view.UserViewModel;

public interface UserService {

    void initUsersAndRoles();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean isUsernameFree(String username);

    UserViewModel getViewModelByUsername(String username);

}
