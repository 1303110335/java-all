/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.queue;

import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author xuleyan
 * @version TestSyncQueue.java, v 0.1 2021-07-26 3:55 下午
 */
@Slf4j
public class TestSyncQueue {

    public static void main(String[] args) throws InterruptedException {
//        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
//        Integer take = queue.take();
//        queue.put(1);
//
//        System.out.println(take);
        testTakeAndPut();
    }

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void testTakeAndPut() throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);


//        ThreadPoolExecutor fixThreadPool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>(),
//                new NamedThreadFactory("sync-thread", true),
//                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                log.info("put thread start");
                try {
                    int number = atomicInteger.incrementAndGet();
                    log.info("put {}", number);
                    queue.put(number);
                } catch (InterruptedException e) {
                }
                log.info("put thread end");
            });
        }

        ExecutorService executorService2 = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService2.execute(() -> {
                log.info("take thread start");
                try {
                    System.out.println("take from putThread: " + queue.take());
                } catch (InterruptedException e) {
                }
                log.info("take thread end");
            });
        }

        TimeUnit.SECONDS.sleep(100);
        executorService.shutdown();
        executorService2.shutdown();
    }
}