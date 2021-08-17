/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.controller;

/**
 *
 * @author xuleyan
 * @version MyClassLoaderTest.java, v 0.1 2020-08-21 5:46 下午
 */
public class MyClassLoaderTest {
    public static void main(String[] args) throws Exception {
        // 指定类加载器加载调用
        MyClassLoader classLoader = new MyClassLoader();
        //classLoader.loadClass("com.redis.example.demo.controller.Test").getMethod("test").invoke(null);


        classLoader.loadClass("com.redis.example.demo.dynamicproxy.asm.Programmer")
                .getMethod("code").invoke(null);
    }
}