package com.decrypto.challenge.config;

import com.decrypto.challenge.config.interceptor.xclient.XClientInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfiguration implements WebMvcConfigurer {

    private final XClientInterceptor xclientInterceptor;

    public ApiConfiguration(XClientInterceptor xclientInterceptor) {
        this.xclientInterceptor = xclientInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(this.xclientInterceptor).addPathPatterns("/api/**");
    }

}
