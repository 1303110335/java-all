/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads.daemons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author xuleyan
 * @version SimpleDaemons.java, v 0.1 2020-11-20 11:00 上午
 */
class SimpleDaemons implements Runnable {
    Logger logger = LoggerFactory.getLogger(SimpleDaemons.class);

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                logger.info("run..");
            }
        } catch (InterruptedException e) {
            logger.info("sleep() interrupted");
        }
    }
}