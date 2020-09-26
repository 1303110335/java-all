/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.service;

/**
 *
 * @author xuleyan
 * @version TimeServiceImpl.java, v 0.1 2020-09-11 1:53 下午
 */
public class TimeServiceImpl implements TimeService {


    @Override
    public void execute() {
        System.out.println("我是执行程序");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}