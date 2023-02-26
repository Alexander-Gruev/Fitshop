package com.fitshop.model.binding;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductUpdateBindingModel {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    @Size(min = 5)
    private String description;

}
