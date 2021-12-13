package com.example.fitshop.model.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String clientFullName;

    @Column(nullable = false)
    private int postcode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Long phoneNumber;

    @Column(nullable = false)
    private String paymentMethod;

    @ManyToOne
    private UserEntity client;

    @OneToOne
    private ProductEntity product;

    @Column
    private Instant created;

    public String getProductName() {
        return productName;
    }

    public OrderEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public OrderEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public OrderEntity setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
        return this;
    }

    public int getPostcode() {
        return postcode;
    }

    public OrderEntity setPostcode(int postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public OrderEntity setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public OrderEntity setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public UserEntity getClient() {
        return client;
    }

    public OrderEntity setClient(UserEntity client) {
        this.client = client;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public OrderEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OrderEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    @PrePersist
    public void beforeCreate() {
        this.created = Instant.now();
    }
}
