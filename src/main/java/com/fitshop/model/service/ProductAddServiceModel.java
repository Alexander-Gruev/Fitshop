package com.fitshop.model.service;

import com.fitshop.enums.ProductCategoryEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductAddServiceModel {

    private String name;
    private BigDecimal price;
    private String brandName;
    private ProductCategoryEnum category;
    private String description;
    private MultipartFile picture;

}
