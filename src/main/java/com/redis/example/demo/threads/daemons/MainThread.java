/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads.daemons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author xuleyan
 * @version MainThread.java, v 0.1 2020-11-20 11:01 上午
 */
public class MainThread {

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            // 设置为守护线程，当主线程退出后，守护线程也会停止
            daemon.setDaemon(true); // Must call before start()
            daemon.start();
        }
        System.out.println("All daemons started");
        try {
            TimeUnit.MILLISECONDS.sleep(175);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("thread main status = " + Thread.currentThread().isAlive());
    }

}