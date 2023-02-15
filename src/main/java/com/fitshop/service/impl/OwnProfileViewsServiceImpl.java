package com.fitshop.service.impl;

import com.fitshop.model.view.OwnProfileViewsViewModel;
import com.fitshop.service.OwnProfileViewsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class OwnProfileViewsServiceImpl implements OwnProfileViewsService {

    private int views;

    @Override
    public void onRequest(HttpServletRequest request) {
        if (request.getRequestURL().toString().contains("profile") && request.getMethod().equalsIgnoreCase("get")) {
            views++;
        }
    }

    @Override
    public OwnProfileViewsViewModel getViews() {
        return new OwnProfileViewsViewModel(views);
    }

}
