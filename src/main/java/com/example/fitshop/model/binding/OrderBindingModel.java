package com.example.fitshop.model.binding;

import javax.validation.constraints.*;

public class OrderBindingModel {

    @NotBlank
    private String country;

    @NotBlank
    @Size(min = 4, max = 40)
    private String clientFullName;

    @Positive
    @NotNull
    private int postcode;

    @NotBlank
    @Size(min = 5, max = 30)
    private String address;

    @NotBlank
    @Size(min = 5, max = 20)
    private String email;

    @Positive
    @NotNull
    private Long phoneNumber;

    @NotBlank
    private String paymentMethod;

    public String getCountry() {
        return country;
    }

    public OrderBindingModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public OrderBindingModel setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
        return this;
    }

    public int getPostcode() {
        return postcode;
    }

    public OrderBindingModel setPostcode(int postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OrderBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public OrderBindingModel setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public OrderBindingModel setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }
}
