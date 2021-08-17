/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo;

import java.util.Random;

/**
 *
 * @author xuleyan
 * @version TestRandom.java, v 0.1 2021-03-31 3:27 下午
 */
public class TestRandom {

    private final Random random = new Random();

    public static void main(String[] args) {
        TestRandom testRandom = new TestRandom();
        testRandom.run();
    }

    private void run() {
        for (int i = 0 ; i < 10; i++) {
            System.out.print(random.nextInt(100) + " , ");
        }
        System.out.println();

        for (int i = 0 ; i < 10; i++) {
            System.out.print(random.nextInt(100) + " , ");
        }
        System.out.println();

        for (int i = 0 ; i < 10; i++) {
            System.out.print(random.nextInt(100) + " , ");
        }
        System.out.println();

        for (int i = 0 ; i < 10; i++) {
            System.out.print(random.nextInt(100) + " , ");
        }
    }

}