package com.fitshop.service;

import com.fitshop.model.view.OwnProfileViewsViewModel;

import javax.servlet.http.HttpServletRequest;

public interface OwnProfileViewsService {

    void onRequest(HttpServletRequest request);

    OwnProfileViewsViewModel getViews();

}
