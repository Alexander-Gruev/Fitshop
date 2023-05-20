package com.fitshop.model.entity;

import com.fitshop.constants.Constants;
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
@Table(name = "users", schema = Constants.SCHEMA_NAME)
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
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            schema = Constants.SCHEMA_NAME
    )
    private Set<UserRoleEntity> roles;

    @Column
    private String pictureUrl;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<OrderEntity> orders;
}
