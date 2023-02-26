package com.fitshop.model.binding;

import com.fitshop.enums.ProductCategoryEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductAddBindingModel {

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    @Size(min = 2, max = 20)
    private String brandName;

    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;

    @NotBlank
    @Size(min = 5)
    private String description;

    @NotNull
    private MultipartFile picture;

}
