package com.daila.yousty.converters;

import com.daila.yousty.entity.Style;
import com.daila.yousty.service.StyleService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToStyleConverter implements Converter<String, Style> {
    private final StyleService styleService;

    public StringToStyleConverter(StyleService styleService) {
        this.styleService = styleService;
    }

    @Override
    public Style convert(String id) {
        return styleService.getStyleById(Long.valueOf(id));
    }
}
