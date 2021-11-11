package com.example.fitshop.model.service;

import com.example.fitshop.enums.ProductCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ProductServiceModel {

    private String name;
    private BigDecimal price;
    private String brandName;
    private ProductCategoryEnum category;
    private String description;
    private MultipartFile picture;

    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public ProductServiceModel setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductServiceModel setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public ProductServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
