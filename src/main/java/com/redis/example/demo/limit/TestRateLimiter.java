/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.List;
import java.util.concurrent.Executor;

/**
 *
 * @author xuleyan
 * @version TestRateLimiter.java, v 0.1 2021-07-06 5:19 下午
 */
public class TestRateLimiter {

    final RateLimiter rateLimiter = RateLimiter.create(2.0);

    public static void main(String[] args) {



    }

    void submitTasks(List<Runnable> tasks, Executor executor) {
        for (Runnable task : tasks) {
            rateLimiter.acquire(); // 也许需要等待
            executor.execute(task);
        }
    }
}