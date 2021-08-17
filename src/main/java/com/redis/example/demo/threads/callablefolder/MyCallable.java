/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.threads.callablefolder;

import java.util.concurrent.Callable;

/**
 * @author xuleyan
 * @version MyCallable.java, v 0.1 2021-07-26 3:36 下午
 */
public class MyCallable implements Callable<String> {
    private String name;
    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        return name + "call";
    }
}