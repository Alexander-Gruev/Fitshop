package com.fitshop.model.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
