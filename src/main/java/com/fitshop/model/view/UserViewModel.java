package com.fitshop.model.view;

import com.fitshop.enums.UserExperienceEnum;
import com.fitshop.model.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserViewModel {

    private Long id;
    private String username;
    private UserExperienceEnum experienceLevel;
    private String pictureUrl;
    private Set<OrderEntity> orders;

}
