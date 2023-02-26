package com.fitshop.model.service;

import com.fitshop.enums.UserExperienceEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterServiceModel {

    private String username;
    private String email;
    private UserExperienceEnum experienceLevel;
    private String password;

}
