package com.fitshop.model.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductUpdateServiceModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;

}
