package com.example.fitshop.model.view;

import java.math.BigDecimal;

public class ProductDetailsViewModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String brand;
    private String description;

    public Long getId() {
        return id;
    }

    public ProductDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDetailsViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ProductDetailsViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
