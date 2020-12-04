/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.test;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author xuleyan
 * @version TestUUid.java, v 0.1 2020-09-18 10:35 上午
 */
public class TestUUid {

    public static void main(String[] args) {



        //URLEncoder.encode("j09rN46F3IeiABexcmc5ug%3d%3d", StandardCharsets.UTF_8);

//        int[] ints = new int[]{1,2,3};
//        System.arraycopy(ints, 2, ints, 1, 1);
//        ints[2] = 0;
//        for (int anInt : ints) {
//            System.out.println(anInt);
//        }

//        BigDecimal bigDecimal = new BigDecimal("100.000");
//        System.out.println(bigDecimal.toString());
//        System.out.println(bigDecimal.stripTrailingZeros().toString());
//        System.out.println(bigDecimal.stripTrailingZeros().toPlainString());

        //testMap();
//        testUUid();

        System.out.println(Integer.MAX_VALUE);
    }

    public static void testUUid() {
        for (int i = 0 ; i < 10; i ++) {
            System.out.println(UUID.randomUUID().toString().substring(0, 8));
        }
    }

    public static void testMap() {
        Map<String, Integer> maps = new HashMap<>();
        //maps.put("name", 123);
        // 如果有了，直接返回，如果没有则设置一个0
        Integer val = maps.computeIfAbsent("name", k -> 0);
        System.out.println(val);
        System.out.println(maps);


    }
}