package com.example.fitshop.model.view;

import com.example.fitshop.enums.UserExperienceEnum;

public class UserViewModel {

    private Long id;
    private String username;
    private UserExperienceEnum experienceLevel;
    private String pictureUrl;


    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public UserViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
