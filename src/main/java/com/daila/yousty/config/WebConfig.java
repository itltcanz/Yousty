package com.daila.yousty.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imagesDir = "file:" + System.getProperty("user.dir") + "/images/";
        System.out.println(imagesDir);
        registry.addResourceHandler("/images/**")
                .addResourceLocations(imagesDir);
    }
}