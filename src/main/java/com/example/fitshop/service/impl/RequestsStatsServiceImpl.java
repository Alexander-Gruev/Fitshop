package com.example.fitshop.service.impl;

import com.example.fitshop.model.view.RequestsStatsViewModel;
import com.example.fitshop.service.RequestsStatsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class RequestsStatsServiceImpl implements RequestsStatsService {

    private int authenticatedRequests, guestRequests;

    @Override
    public void onRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            authenticatedRequests ++;
        } else {
            guestRequests ++;
        }
    }

    @Override
    public RequestsStatsViewModel getStats() {
        return new RequestsStatsViewModel(authenticatedRequests, guestRequests);
    }


}
