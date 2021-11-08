package com.example.fitshop.model.entity;

import com.example.fitshop.enums.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRoleEnum roleEnum;

    public UserRoleEnum getRoleEnum() {
        return roleEnum;
    }

    public UserRoleEntity setRoleEnum(UserRoleEnum roleEnum) {
        this.roleEnum = roleEnum;
        return this;
    }
}
