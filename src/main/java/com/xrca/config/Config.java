package com.xrca.config;

import com.xrca.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xrca
 * @description 配置
 * @date 2020/11/6 22:31
 **/
@Configuration
public class Config implements WebMvcConfigurer {

    /**
     * @author xrca
     * @description 添加拦截器
     * @date 2020/12/6 15:33
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
