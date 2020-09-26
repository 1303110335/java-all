/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.service;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author xuleyan
 * @version TimeServiceImplProxy.java, v 0.1 2020-09-11 1:55 下午
 */
@Slf4j
public class TimeServiceImplProxy implements InvocationHandler {

    private TimeService timeService;

    public TimeService createProxy(TimeService timeService) {
        this.timeService = timeService;
        return (TimeService) Proxy.newProxyInstance(TimeServiceImplProxy.class.getClassLoader(), timeService.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log.info("任务开始 >> method = {}", method.getName());
        long start = System.currentTimeMillis();
        Object result = method.invoke(timeService, args);
        long end = System.currentTimeMillis();
        log.info("任务结束 >> method = {} >> 持续时间 >> time = {}ms", method.getName(), (end - start));
        return result;
    }
}