/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * @author xuleyan
 * @version BeanTest.java, v 0.1 2021-06-08 10:21 上午
 */
@Data
@Slf4j
public class BeanTest implements InitializingBean {

    private String name;

    @PostConstruct
    public void init() {
        log.info("init haha ...");
        this.name = "haha";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet ...");

    }
}