package com.example.fitshop.config;

import com.example.fitshop.web.interceptor.OwnProfileViewsInterceptor;
import com.example.fitshop.web.interceptor.RequestsStatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final RequestsStatsInterceptor requestsStatsInterceptor;
    private final OwnProfileViewsInterceptor ownProfileViewsInterceptor;

    public WebConfiguration(RequestsStatsInterceptor requestsStatsInterceptor, OwnProfileViewsInterceptor ownProfileViewsInterceptor) {
        this.requestsStatsInterceptor = requestsStatsInterceptor;
        this.ownProfileViewsInterceptor = ownProfileViewsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.requestsStatsInterceptor);
        registry.addInterceptor(this.ownProfileViewsInterceptor);
    }
}
