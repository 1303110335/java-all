/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.dynamicproxy.cl;

/**
 *
 * @author xuleyan
 * @version MyClassLoader.java, v 0.1 2021-07-18 9:19 下午
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineMyClass( byte[] b, int off, int len)
    {
        return super.defineClass(null, b, off, len);
    }

}