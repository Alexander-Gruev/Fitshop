package com.example.fitshop.model.service;

import com.example.fitshop.enums.ProductCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ProductAddServiceModel {

    private String name;
    private BigDecimal price;
    private String brandName;
    private ProductCategoryEnum category;
    private String description;
    private MultipartFile picture;

    public String getName() {
        return name;
    }

    public ProductAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public ProductAddServiceModel setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductAddServiceModel setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public ProductAddServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
