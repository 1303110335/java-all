/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.proxy;

/**
 *
 * @author xuleyan
 * @version TargetObject.java, v 0.1 2020-10-23 4:04 下午
 */
/**
 * 实现了接口MyInterface和接口的play()方法，可以作为被代理类
 */
public class TargetObject implements MyInterface {

    @Override
    public void play() {
        System.out.println("妲己，陪你玩 ~");
    }
}
