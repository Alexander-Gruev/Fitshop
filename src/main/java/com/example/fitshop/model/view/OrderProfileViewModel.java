package com.example.fitshop.model.view;

import com.example.fitshop.model.entity.ProductEntity;

import java.time.Instant;

public class OrderProfileViewModel {

    private String productName;
    private String productBrandName;
    private String address;
    private Instant created;

    public String getProductName() {
        return productName;
    }

    public OrderProfileViewModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public OrderProfileViewModel setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderProfileViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OrderProfileViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }
}
