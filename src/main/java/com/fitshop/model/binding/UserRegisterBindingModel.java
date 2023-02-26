package com.fitshop.model.binding;

import com.fitshop.enums.UserExperienceEnum;
import com.fitshop.model.validator.UniqueEmail;
import com.fitshop.model.validator.UniqueUsername;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegisterBindingModel {

    @UniqueUsername(message = "Username is already taken!")
    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols.")
    private String username;

    @UniqueEmail(message = "Email is already taken!")
    @NotBlank
    @Size(min = 5, max = 40, message = "Email must be between 5 and 40 symbols.")
    private String email;

    @NotNull
    private UserExperienceEnum experienceLevel;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    @NotBlank
    @Size(min = 3, max = 20)
    private String confirmPassword;

}
