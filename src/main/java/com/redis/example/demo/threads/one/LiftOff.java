/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads.one;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuleyan
 * @version LiftOff.java, v 0.1 2020-11-20 10:54 上午
 */

class LiftOff implements Runnable {

    Logger logger = LoggerFactory.getLogger(LiftOff.class);

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(status());
            Thread.yield();
        }
    }
}