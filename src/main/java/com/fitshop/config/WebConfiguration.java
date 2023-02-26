package com.fitshop.config;

import com.fitshop.web.interceptor.OwnProfileViewsInterceptor;
import com.fitshop.web.interceptor.RequestsStatsInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final RequestsStatsInterceptor requestsStatsInterceptor;
    private final OwnProfileViewsInterceptor ownProfileViewsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.requestsStatsInterceptor);
        registry.addInterceptor(this.ownProfileViewsInterceptor);
    }
}
