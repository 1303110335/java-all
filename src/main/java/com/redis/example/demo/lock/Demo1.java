/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 *
 * @author xuleyan
 * @version Demo1.java, v 0.1 2021-07-15 3:36 下午
 */
@Slf4j
public class Demo1 {

    public void lock1(InterProcessMutex lock) throws Exception {
        lock.acquire();
        log.info("lock1成功获取锁");
        lock2(lock);
        lock.release();
        log.info("lock1成功释放锁");


    }

    public void lock2(InterProcessMutex lock) throws Exception {
        lock.acquire();
        log.info("lock2成功获取锁");
        Thread.sleep(1000*10);

        lock.release();
        log.info("lock2成功释放锁");
    }

    public static void main(String[] args) throws Exception {
        ZkLock zkLock = new ZkLock();
        InterProcessMutex lock = zkLock.getLock("demo");
        Demo1 demo1 = new Demo1();
        demo1.lock1(lock);

    }
}