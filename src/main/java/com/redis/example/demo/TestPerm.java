/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo;

/**
 *
 * @author xuleyan
 * @version TestPerm.java, v 0.1 2021-01-06 2:14 下午
 */
public class TestPerm {

    public static void main(String[] args) {
        int perm = 7;
        // 1 = 00000001
        // ~1 = 11111110

        // 2 = 00000010
        // 取反 = 11111101
        // -2 = 取反+1 = 11111110

        // 7 = 00000111
        // 7 &= ~1   =  00000111 & 11111110 = 00000110 = 6
        System.out.println(perm &= ~1);
    }
}