package com.example.fitshop.service;

import com.example.fitshop.model.service.UserRegisterServiceModel;

public interface UserService {

    void initUsersAndRoles();

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean isUsernameFree(String username);


}
