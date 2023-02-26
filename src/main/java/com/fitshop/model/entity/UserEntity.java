package com.fitshop.model.entity;

import com.fitshop.enums.UserExperienceEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
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
}
