package com.itliv.community.config;

import com.itliv.community.config.interceptor.LoginInterceptor;
import com.itliv.community.config.interceptor.LoginInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 */
@Configuration
public class MyWebAppConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor2()).addPathPatterns("/**").excludePathPatterns("/index");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }
}
