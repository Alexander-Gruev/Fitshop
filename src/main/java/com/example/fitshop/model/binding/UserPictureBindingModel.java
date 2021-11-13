package com.example.fitshop.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class UserPictureBindingModel {

    private MultipartFile picture;

    public MultipartFile getPicture() {
        return picture;
    }

    public UserPictureBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
