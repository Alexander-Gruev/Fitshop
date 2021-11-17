package com.example.fitshop.model.view;

import java.time.Instant;

public class OrderViewModel {

    private String productName;
    private String clientFullName;
    private Instant created;

    public String getProductName() {
        return productName;
    }

    public OrderViewModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public OrderViewModel setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OrderViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }
}
