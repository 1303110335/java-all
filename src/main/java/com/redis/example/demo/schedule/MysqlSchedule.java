/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.schedule;

import com.redis.example.demo.utils.DateTimeUtils;
import com.xuleyan.frame.extend.redis.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * @author xuleyan
 * @version Test.java, v 0.1 2020-09-09 8:52 下午
 */
@Service
@Slf4j
@EnableAsync
public class MysqlSchedule {

    @Autowired
    private JedisTemplate jedisTemplate;

    /**
     * 同步执任务
     */
//    @Async("taskScheduler")
//    @Scheduled(cron = "0/10 0/1 * * * ?")
    public void schedulerAsync() {
        Date now = new Date();
        String start = DateTimeUtils.format(now, DateTimeUtils.NORMAL_DATETIME_PATTERN);
        log.info("开始同步任务: start = {}", start);
        // 获取上次的执行开始时间
        String preEnd = jedisTemplate.get(DATAX_MYSQLTOES_PRE_START);
        // 本次需要同步的数据的默认时间区间 2s
        int executeTime = 2;
        if (StringUtils.isNotBlank(preEnd)) {
            // 如果有上次的执行结束时间，则本次需要同步的数据的时间区间为 【这次的开始时间-上次的结束时间】
            Long rangeTime = now.getTime() - DateTimeUtils.parse(preEnd, DateTimeUtils.NORMAL_DATETIME_PATTERN).getTime();
            executeTime = rangeTime.intValue() / 1000;
        }
        // 保存本次的执行开始时间
        jedisTemplate.set(DATAX_MYSQLTOES_PRE_START, start);

        for (int i = 0; i< 4; i++) {
            try {
                Thread.sleep(3000);
                log.info("第{}个任务sleep 3s", i);
                log.info("第{}个任务,本次处理的时间跨度为{}s, 上一次的结束时间为{}", i, executeTime, preEnd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String end = DateTimeUtils.format(new Date(), DateTimeUtils.NORMAL_DATETIME_PATTERN);
        log.info("结束同步任务: date = {}", end);
    }

    /**
     * 异步多线程执行任务
     */
//    @Async("taskScheduler")
//    @Scheduled(cron = "0/3 0/1 * * * ?")
    public void scheduler() {
        String start = DateTimeUtils.format(new Date(), DateTimeUtils.NORMAL_DATETIME_PATTERN);
        log.info("开始同步任务: date = {}", start);
        jedisTemplate.set("startTime", start);
        try {
            Thread.sleep(15000);
            log.info("sleep 15s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String end = DateTimeUtils.format(new Date(), DateTimeUtils.NORMAL_DATETIME_PATTERN);
        log.info("结束同步任务: date = {}", end);
    }

    /**
     * 当天第一次开始跑的时间点，保存到redis的key
     */
    private static String DATAX_MYSQLTOES_PRE_START = "datax.mysqltoes.pre.start";

    /**
     * 同步执任务
     */
//    @Scheduled(cron = "0/10 0/1 * * * ?")
    public void schedulerSync() {
        Date now = new Date();
        String start = DateTimeUtils.format(now, DateTimeUtils.NORMAL_DATETIME_PATTERN);
        log.info("开始同步任务: start = {}", start);
        // 获取上次的执行开始时间
        String preEnd = jedisTemplate.get(DATAX_MYSQLTOES_PRE_START);
        // 本次需要同步的数据的默认时间区间 10s
        int executeTime = 10;
        if (StringUtils.isNotBlank(preEnd)) {
            // 如果有上次的执行结束时间，则本次需要同步的数据的时间区间为 【这次的开始时间-上次的结束时间】
            Long rangeTime = now.getTime() - DateTimeUtils.parse(preEnd, DateTimeUtils.NORMAL_DATETIME_PATTERN).getTime();
            executeTime = rangeTime.intValue() / 1000;
        }
        log.info("本次处理的时间跨度为{}s, 上一次的结束时间为{}", executeTime, preEnd);


        // 保存本次的执行开始时间
        jedisTemplate.set(DATAX_MYSQLTOES_PRE_START, start);
        try {
            Thread.sleep(15000);
            log.info("sleep 15s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String end = DateTimeUtils.format(new Date(), DateTimeUtils.NORMAL_DATETIME_PATTERN);
        log.info("结束同步任务: date = {}", end);
    }



}