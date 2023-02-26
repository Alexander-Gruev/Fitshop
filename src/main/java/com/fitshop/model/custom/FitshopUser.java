package com.fitshop.model.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class FitshopUser extends User {

    private String experienceLevel;

    public FitshopUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String experienceLevel ) {
        super(username, password, authorities);
        this.experienceLevel = experienceLevel;
    }

}
