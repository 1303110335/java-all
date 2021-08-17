/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version WebConfigurer.java, v 0.1 2021-04-11 7:23 下午
 */
@Configuration
@Slf4j
public class WebConfigurer implements WebMvcConfigurer {

    @Resource
    private MaskingStringHttpMessageConverter maskingStringHttpMessageConverter;

    @Resource
    private MaskingJackson2HttpMessageConverter maskingJackson2HttpMessageConverter;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (int i = 0; i < converters.size(); i++) {
            //log.info("configureMessageConverters >> class = {}", converters.get(i).getClass());

            if (converters.get(i).getClass().equals(MappingJackson2HttpMessageConverter.class)) {
                converters.set(i, maskingJackson2HttpMessageConverter);
            }
            if (converters.get(i).getClass().equals(StringHttpMessageConverter.class)) {
                converters.set(i, maskingStringHttpMessageConverter);
            }

        }
    }
}