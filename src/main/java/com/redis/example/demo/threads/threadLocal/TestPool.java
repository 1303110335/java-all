/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.threads.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池中的线程被复用，threadLocal中的值也会被复用，所以每次用完线程之后都要手动remove该threadLocal
 * @author xuleyan
 * @version TestPool.java, v 0.1 2021-07-30 8:16 下午
 */
public class TestPool {

    public static ThreadLocal<Integer> valueHolder = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static int getValue() {
        return valueHolder.get();
    }

    public static void remove() {
        valueHolder.remove();
    }

    public static void increment() {
        valueHolder.set(valueHolder.get() + 1);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    long threadId = Thread.currentThread().getId();
                    int before = getValue();
                    increment();
                    int after = getValue();
                    System.out.println("threadId: " + threadId + ", before: " + before + ", after: " + after);
                } finally {
                    remove();
                }
            });
        }
        executorService.shutdown();
    }
}