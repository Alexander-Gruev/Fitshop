package com.example.fitshop.model.service;

import org.springframework.web.multipart.MultipartFile;

public class UserPictureServiceModel {

    private MultipartFile picture;
    private String username;

    public MultipartFile getPicture() {
        return picture;
    }

    public UserPictureServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserPictureServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
