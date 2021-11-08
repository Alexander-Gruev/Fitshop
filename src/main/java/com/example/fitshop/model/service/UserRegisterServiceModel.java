package com.example.fitshop.model.service;

import com.example.fitshop.enums.UserExperienceEnum;

public class UserRegisterServiceModel {

    private String username;
    private String email;
    private UserExperienceEnum experienceLevel;
    private String password;

    public String getUsername() {
        return username;
    }

    public UserRegisterServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserExperienceEnum getExperience() {
        return experienceLevel;
    }

    public UserRegisterServiceModel setExperience(UserExperienceEnum experienceLevel) {
        this.experienceLevel = experienceLevel;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
