package com.example.fitshop;

import com.example.fitshop.enums.ProductCategoryEnum;
import com.example.fitshop.enums.UserExperienceEnum;

import java.math.BigDecimal;

public class GlobalTestConstants {

    public static final String PRODUCT_NAME = "Bench";
    public static final String PRODUCT_BRAND_NAME = "Rogue";
    public static final String PRODUCT_DESCRIPTION = "Nice bench";
    public static final String PRODUCT_IMG_URL = "url/test";
    public static final BigDecimal PRODUCT_PRICE = BigDecimal.TEN;
    public static final ProductCategoryEnum PRODUCT_CATEGORY = ProductCategoryEnum.WEIGHTS;

    public static final String USERNAME = "Sasho123";
    public static final String PASSWORD = "password";
    public static final UserExperienceEnum USER_EXPERIENCE = UserExperienceEnum.ADVANCED;
    public static final String EMAIL = "abc@abv.bg";

    public static final String ORDER_COUNTRY = "Germany";
    public static final String ORDER_CLIENT_FULL_NAME = "Pesho Petrov";
    public static final String ORDER_POSTCODE = "1000";
    public static final String ORDER_ADDRESS = "Vitoshka 15";
    public static final String ORDER_EMAIL = "pesho@abv.bg";
    public static final String ORDER_PHONE_NUMBER = "123456789";
    public static final String ORDER_PAYMENT_METHOD = "Cash";

    public static final String NEW_ORDER_VIEW_NAME = "order-new";
    
    public static final String INDEX_VIEW_NAME = "index";
    public static final String ABOUT_VIEW_NAME = "about";
    public static final String BEGINNER_VIEW_NAME = "beginner";
    public static final String INTERMEDIATE_VIEW_NAME = "intermediate";
    public static final String ADVANCED_VIEW_NAME = "advanced";
    
    public static final String CREATED_STRING = "2018-11-30T18:35:24.00Z";
    
    public static final String USER_LEVEL_STRING = "ADVANCED";
    
    public static final String NON_EXISTENT_USERNAME = "Gosho";
    
    public static final String EXPECTED_ROLES_STRING = "ROLE_ADMIN, ROLE_USER";
    
    public static final String CLIENT_FULL_NAME = "Pesho Petrov";

}
