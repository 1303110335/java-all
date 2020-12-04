/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author xuleyan
 * @version ReentrantLockTest.java, v 0.1 2020-11-22 2:10 下午
 */
public class ReentrantLockTest {



    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        try {
            if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
                System.out.println("加锁成功");
            } else {
                System.out.println("加锁失败");
            }
        } finally {
            lock.unlock();
        }


    }
}