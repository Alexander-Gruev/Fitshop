package com.example.fitshop.repository;

import com.example.fitshop.enums.UserRoleEnum;
import com.example.fitshop.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRoleEnum(UserRoleEnum roleEnum);
}
