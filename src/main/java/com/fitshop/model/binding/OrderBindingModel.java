package com.fitshop.model.binding;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
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

}
