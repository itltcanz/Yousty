package com.daila.yousty.config;

import com.daila.yousty.converters.StringToStyleConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final StringToStyleConverter stringToStyleConverter;

    public WebMvcConfig(StringToStyleConverter stringToStyleConverter) {
        this.stringToStyleConverter = stringToStyleConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToStyleConverter);
    }
}
