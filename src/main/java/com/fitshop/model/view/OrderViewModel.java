package com.fitshop.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderViewModel {

    private String productName;
    private String clientFullName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "EET")
    private Instant created;

}
