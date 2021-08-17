/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.fastjson;

import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * 简单脱敏
 * @author xuleyan
 * @version SimpleValueFilter.java, v 0.1 2021-04-11 3:53 下午
 */
@Slf4j
public class SimpleValueFilter implements ValueFilter {

    public SimpleValueFilter() {
        log.info("SimpleValueFilter >> 初始化");
    }

    @Override
    public Object process(Object object, String name, Object value) {
        if ("mobile".equals(name.toLowerCase())) {
            return "*****";
        }
        return value;
    }
}