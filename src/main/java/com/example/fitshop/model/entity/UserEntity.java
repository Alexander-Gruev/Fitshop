package com.example.fitshop.model.entity;

import com.example.fitshop.enums.UserExperienceEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserExperienceEnum experienceLevel;

    @ManyToMany
    private Set<UserRoleEntity> roles;

    @Column
    private String pictureUrl;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<OrderEntity> orders;

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserExperienceEnum getExperienceLevel() {
        return experienceLevel;
    }

    public UserEntity setExperienceLevel(UserExperienceEnum experienceLevel) {
        this.experienceLevel = experienceLevel;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public UserEntity setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public UserEntity setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }
}
