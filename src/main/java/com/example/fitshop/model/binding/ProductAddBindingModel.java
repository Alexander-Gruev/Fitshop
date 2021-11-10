package com.example.fitshop.model.binding;

import com.example.fitshop.enums.ProductCategoryEnum;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

public class ProductAddBindingModel {

    private String name;
    private BigDecimal price;
    private String brand;
    private ProductCategoryEnum category;
    private String description;
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

    public String getBrand() {
        return brand;
    }

    public ProductAddBindingModel setBrand(String brand) {
        this.brand = brand;
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
