package com.example.fitshop.model.view;

import java.math.BigDecimal;

public class ProductViewModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String brand;

    public Long getId() {
        return id;
    }

    public ProductViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ProductViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }
}
