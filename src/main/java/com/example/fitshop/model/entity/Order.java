package com.example.fitshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String clientFullName;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String paymentMethod;


    public String getProductName() {
        return productName;
    }

    public Order setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Order setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public Order setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public Order setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Order setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Order setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }
}
