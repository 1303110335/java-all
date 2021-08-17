/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.redis.example.demo.fastjson.SimpleValueFilter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 *
 * @author xuleyan
 * @version MaskingJackson2HttpMessageConverter.java, v 0.1 2021-04-11 8:57 下午
 */
@Component
public class MaskingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        //object = JSON.parse(JSON.toJSONString(object, new SimpleValueFilter()));
        super.writeInternal(object, type, outputMessage);
    }
}