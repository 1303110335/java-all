/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author xuleyan
 * @version TestThreadExist.java, v 0.1 2021-07-26 4:23 下午
 */
public class TestThreadExist {

    public volatile boolean exist = false;

    private volatile AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        TestThreadExist testThreadExist = new TestThreadExist();
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("修改exist=true");
//                testThreadExist.exist = true;
//            }
//        });
//        thread.start();
        testThreadExist.test();

    }

    private void test() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("haha");
                    int i = integer.incrementAndGet();
                    System.out.println("i=" + i);
                    if (i == 1000) {
                        //boolean interrupted = Thread.interrupted();
                        break;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("interrupt");
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });

        thread.start();
//        TimeUnit.SECONDS.sleep(1);
        // 主线程需要等待thread线程运行完毕之后才能运行
        thread.join();

        System.out.println("thread.interrupt.....");
        thread.interrupt();

    }
}