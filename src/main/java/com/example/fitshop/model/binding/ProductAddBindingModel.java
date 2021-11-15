package com.example.fitshop.model.binding;

import com.example.fitshop.enums.ProductCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

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

    public String getName() {
        return name;
    }

    public ProductAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public ProductAddBindingModel setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductAddBindingModel setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public ProductAddBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
