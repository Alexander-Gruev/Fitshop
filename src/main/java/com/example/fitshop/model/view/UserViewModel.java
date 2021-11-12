package com.example.fitshop.model.view;

import com.example.fitshop.enums.UserExperienceEnum;

public class UserViewModel {

    private String username;
    private UserExperienceEnum experienceLevel;

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserExperienceEnum getExperienceLevel() {
        return experienceLevel;
    }

    public UserViewModel setExperienceLevel(UserExperienceEnum experienceLevel) {
        this.experienceLevel = experienceLevel;
        return this;
    }
}
