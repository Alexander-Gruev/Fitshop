package com.fitshop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @PrePersist
    public void beforeCreate() {
        this.created = Instant.now();
    }
}
