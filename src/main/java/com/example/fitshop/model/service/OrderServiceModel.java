package com.example.fitshop.model.service;

public class OrderServiceModel {

    private String productName;
    private String country;
    private String clientFullName;
    private int postcode;
    private String address;
    private String email;
    private Long phoneNumber;
    private String paymentMethod;
    private String clientUsername;

    public String getProductName() {
        return productName;
    }

    public OrderServiceModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public OrderServiceModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public OrderServiceModel setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
        return this;
    }

    public int getPostcode() {
        return postcode;
    }

    public OrderServiceModel setPostcode(int postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OrderServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public OrderServiceModel setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public OrderServiceModel setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public OrderServiceModel setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
        return this;
    }
}
