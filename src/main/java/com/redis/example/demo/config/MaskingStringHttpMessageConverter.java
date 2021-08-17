/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.redis.example.demo.fastjson.SimpleValueFilter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 * @author xuleyan
 * @version MaskingStringHttpMessageConverter.java, v 0.1 2021-04-11 8:23 下午
 */
@Component
public class MaskingStringHttpMessageConverter extends StringHttpMessageConverter {

    @Override
    protected void writeInternal(String str, HttpOutputMessage outputMessage) throws IOException {
        str = JSON.toJSONString(JSON.parseObject(str), new SimpleValueFilter());
        outputMessage.getHeaders().setContentLength(str.getBytes().length);
        super.writeInternal(str, outputMessage);
    }
}