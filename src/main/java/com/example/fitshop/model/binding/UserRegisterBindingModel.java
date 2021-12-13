package com.example.fitshop.model.binding;

import com.example.fitshop.enums.UserExperienceEnum;
import com.example.fitshop.model.validator.UniqueEmail;
import com.example.fitshop.model.validator.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserExperienceEnum getExperienceLevel() {
        return experienceLevel;
    }

    public UserRegisterBindingModel setExperienceLevel(UserExperienceEnum experienceLevel) {
        this.experienceLevel = experienceLevel;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
