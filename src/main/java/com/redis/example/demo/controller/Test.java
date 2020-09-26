/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.controller;

/**
 *
 * @author xuleyan
 * @version Test.java, v 0.1 2020-08-21 4:54 下午
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("Test类已成功加载运行！");
        ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println("加载我的classLoader：" + classLoader);
        System.out.println("classLoader.parent：" + classLoader.getParent());
    }
}