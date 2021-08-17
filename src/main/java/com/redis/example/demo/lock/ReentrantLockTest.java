/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.lock;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuleyan
 * @version ReentrantLockTest.java, v 0.1 2020-11-22 2:10 下午
 */
public class ReentrantLockTest extends Thread {
    ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ReentrantLockTest test = new ReentrantLockTest();
        for (int i = 0; i < 10; i++) {
            executorService.execute(test);
        }

        executorService.shutdown();
    }

    @Override
    public void run() {

        try {
            if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " " + lock.getQueueLength());
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 加锁成功");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " 抛出异常 e = " + e.getMessage());
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " 超时导致加锁失败");
            }
        } catch (InterruptedException e) {
            System.out.println("加锁异常");
        }

    }
}