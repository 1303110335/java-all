/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xuleyan
 * @version TestSee.java, v 0.1 2021-08-08 10:43 上午
 */
public class TestSee {



    public static void main(String[] args) {
        AtomicBoolean flag = new AtomicBoolean(true);
        Thread thread = new Thread(() -> {
            while (flag.get()) {
                System.out.println("flag");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            flag.set(false);
            System.out.println("修改flag为false");

            try {
                TimeUnit.MILLISECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
    }
}