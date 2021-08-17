/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads.one;

import com.redis.example.demo.threads.ProcThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author xuleyan
 * @version MainThread.java, v 0.1 2020-11-20 10:56 上午
 */
public class MainThread {

    public static void main(String[] args) throws InterruptedException {
//        Thread t = new Thread(new LiftOff());
//        t.start();
//        t.setName("lift off thread");
//        System.out.println("waiting for liftoff");


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 1000L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), r -> {
            Thread thread = new Thread(r);
            thread.setName("testMainThread");
            return thread;
        });

        for (int i = 0; i < 2; i++) {
            threadPoolExecutor.execute(new ProcThread(i, null));
//            Future<?> submit = threadPoolExecutor.submit(new ProcThread(i, null));
//            Object o = submit.get();
        }

        threadPoolExecutor.shutdown();
//        t.join();
//        System.out.println("thread t status = " + t.isAlive());
//        System.out.println("thread main status = " + Thread.currentThread().isAlive());
    }

}