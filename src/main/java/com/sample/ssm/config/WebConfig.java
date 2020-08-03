package com.sample.ssm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: ssm
 * @description: 配置器
 * web请求拦截器 WebRequestInterceptors
 * 处理器拦截器 HandlerInterceptors
 * @author: baijd-a
 * @create: 2020-07-15 10:40
 **/
//@Configuration
//@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private HandlerInterceptor shiroHandlerInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shiroHandlerInterceptorAdapter)
                .addPathPatterns("/hello/*")
                .addPathPatterns("/permission/*")
                .excludePathPatterns("/register/*")
                .excludePathPatterns("/login/*")
                .excludePathPatterns("/error/*");
    }
}
