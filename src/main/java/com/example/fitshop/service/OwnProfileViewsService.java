package com.example.fitshop.service;

import com.example.fitshop.model.view.OwnProfileViewsViewModel;

import javax.servlet.http.HttpServletRequest;

public interface OwnProfileViewsService {

    void onRequest(HttpServletRequest request);

    OwnProfileViewsViewModel getViews();

}
