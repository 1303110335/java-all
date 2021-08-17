/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author xuleyan
 * @version TestMap.java, v 0.1 2021-04-27 3:10 下午
 */
@Slf4j
public class TestMap {
    private static volatile Map<String, List<String>> code2connectionPool = new HashMap<>();

    private Object object = new Object();
    public static void main(String[] args) {
//        code2connectionPool.put("001", new ArrayList<>());
//
//        List<String> connections = code2connectionPool.get("001");
//        connections.add("123");
//
//        System.out.println(code2connectionPool);

        AtomicInteger index = new AtomicInteger(0);

        ExecutorService executorService = Executors.newFixedThreadPool(9);
        for (int i = 0; i < 9; i++) {
            executorService.execute(() -> {
                synchronized (TestMap.class) {
                    int number = index.get();
                    log.info("number = {}", number);
                    index.incrementAndGet();
                    System.out.println(index.get());
                }
            });
        }
        executorService.shutdown();


    }
}