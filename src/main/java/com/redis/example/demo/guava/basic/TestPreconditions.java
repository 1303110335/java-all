/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.guava.basic;

import com.google.common.base.Preconditions;

/**
 *
 * @author xuleyan
 * @version TestPreconditions.java, v 0.1 2021-08-23 11:36 上午
 */
public class TestPreconditions {

    public static void main(String[] args) {
//        checkArgument();

        Integer a = null;
        Preconditions.checkNotNull(a, "a must not be null");
    }

    private static void checkArgument() {
        int i = -1;
        // 不符合条件会跑出异常
        Preconditions.checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
    }
}