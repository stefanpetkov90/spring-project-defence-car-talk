package com.springprojectdefence.configuration;

import com.springprojectdefence.interceptors.TitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final TitleInterceptor titleInterceptor;
    private final HandlerInterceptor logInterceptor;


    @Autowired
    public WebMvcConfig(TitleInterceptor titleInterceptor, HandlerInterceptor logInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.logInterceptor = logInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.titleInterceptor);
        registry.addInterceptor(this.logInterceptor);
    }
}
