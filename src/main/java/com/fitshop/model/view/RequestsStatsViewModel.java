package com.fitshop.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestsStatsViewModel {

    private int authenticatedRequests;
    private int guestRequests;

    public int getTotal() {
        return this.authenticatedRequests + this.guestRequests;
    }
}
