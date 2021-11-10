package com.example.fitshop.model.custom;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class FitshopUser extends User {

    private String experienceLevel;

    public FitshopUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String experienceLevel ) {
        super(username, password, authorities);
        this.experienceLevel = experienceLevel;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public FitshopUser setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
        return this;
    }
}
