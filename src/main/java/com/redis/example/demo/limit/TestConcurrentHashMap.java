/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.limit;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author xuleyan
 * @version TestConcurrentHashMap.java, v 0.1 2021-07-07 9:52 上午
 */
public class TestConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();

        for (int i = 0; i < 100; i++) {
            map.put("name" + i, "xly" + i);
        }
        map.put("course", "history");

    }
}