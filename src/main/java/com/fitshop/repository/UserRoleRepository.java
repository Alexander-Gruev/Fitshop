package com.fitshop.repository;

import com.fitshop.enums.UserRoleEnum;
import com.fitshop.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRoleEnum(UserRoleEnum roleEnum);
}
