package com.example.fitshop.model.service;

import java.math.BigDecimal;

public class ProductUpdateServiceModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;

    public Long getId() {
        return id;
    }

    public ProductUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductUpdateServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductUpdateServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
