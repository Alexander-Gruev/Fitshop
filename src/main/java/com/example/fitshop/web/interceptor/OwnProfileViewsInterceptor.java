package com.example.fitshop.web.interceptor;

import com.example.fitshop.service.OwnProfileViewsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OwnProfileViewsInterceptor implements HandlerInterceptor {

    private final OwnProfileViewsService ownProfileViewsService;

    public OwnProfileViewsInterceptor(OwnProfileViewsService ownProfileViewsService) {
        this.ownProfileViewsService = ownProfileViewsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.ownProfileViewsService.onRequest(request);
        return true;
    }
}
