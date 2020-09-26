/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.service;

/**
 *
 * @author xuleyan
 * @version TimeServiceDemo.java, v 0.1 2020-09-11 2:05 下午
 */
public class TimeServiceDemo {
    public static void main(String[] args) {
        TimeServiceImplProxy proxy = new TimeServiceImplProxy();
        TimeService service = proxy.createProxy(new TimeServiceImpl());
        service.execute();
    }
}