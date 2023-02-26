package com.fitshop.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProfileViewModel {

    private String productName;
    private String productBrandName;
    private String address;
    private Instant created;

}
