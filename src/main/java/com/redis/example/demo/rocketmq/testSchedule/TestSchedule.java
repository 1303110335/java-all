/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.rocketmq.testSchedule;

import com.redis.example.demo.utils.DateTimeUtils;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author xuleyan
 * @version TestSchedule.java, v 0.1 2020-09-27 2:47 下午
 */
public class TestSchedule {

    public static void main(String[] args) {
        TestSchedule test = new TestSchedule();
//        test.submitConsume("lele");
        test.submitConsumeFixRate();
    }

    /**
     * 延迟一定时间执行
     * @param name
     */
    public void submitConsume(String name) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.schedule(() -> {
            String format = DateTimeUtils.format(new Date(), DateTimeUtils.NORMAL_DATETIME_PATTERN);
            System.out.println(name + "," + format);
        }, 500, TimeUnit.MILLISECONDS);

    }

    /**
     * 固定时间执行
     */
    public void submitConsumeFixRate() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            String format = DateTimeUtils.format(new Date(), DateTimeUtils.NORMAL_DATETIME_PATTERN);
            System.out.println(format);
        }, 500, 500, TimeUnit.MILLISECONDS);
    }
}