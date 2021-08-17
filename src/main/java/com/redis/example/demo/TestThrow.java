/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo;

import lombok.SneakyThrows;

/**
 *
 * @author xuleyan
 * @version TestThrow.java, v 0.1 2021-04-08 3:00 下午
 */
public class TestThrow {

    public static void main(String[] args) {
        throwException();
    }

    @SneakyThrows(value = NullPointerException.class)
    private static void throwException() {
        String str = null;
        String[] split = str.split(",");
        System.out.println(split);

    }
}