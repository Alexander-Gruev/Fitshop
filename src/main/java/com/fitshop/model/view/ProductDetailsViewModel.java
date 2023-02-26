package com.fitshop.model.view;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDetailsViewModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String brand;
    private String description;

}
