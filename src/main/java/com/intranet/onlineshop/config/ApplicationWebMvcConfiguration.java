package com.intranet.onlineshop.config;

import com.intranet.onlineshop.web.interceptors.ActivityInterceptor;
import com.intranet.onlineshop.web.interceptors.TitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class used for registering the custom interceptors
 */
@Configuration
public class ApplicationWebMvcConfiguration implements WebMvcConfigurer {

    private final TitleInterceptor titleInterceptor;
    private final ActivityInterceptor activityInterceptor;

    @Autowired
    public ApplicationWebMvcConfiguration(TitleInterceptor titleInterceptor, ActivityInterceptor activityInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.activityInterceptor = activityInterceptor;
    }

    /**
     * registering the interceptors
     * @param registry used for registering
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(titleInterceptor);
        registry.addInterceptor(activityInterceptor);
    }
}
