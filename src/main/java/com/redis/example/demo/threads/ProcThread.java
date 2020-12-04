/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads;

import com.alibaba.fastjson.JSON;
import com.redis.example.demo.service.JdbcService;
import com.redis.example.demo.service.JdbcServicePool;
import com.redis.example.demo.utils.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 *
 * @author xuleyan
 * @version ProcThread.java, v 0.1 2020-09-25 2:25 下午
 */
@Slf4j
public class ProcThread implements Runnable {

    private Integer num;

    private JdbcServicePool jdbcService;

    public ProcThread(Integer num, JdbcServicePool jdbcService) {
        this.num = num;
        this.jdbcService = jdbcService;
    }

    @Override
    public void run() {
        //ResponseWrapper<String> responseWrapper = jdbcService.execProcedure(new Date(), "p", 15);
//        log.warn("result = {}, threadName = {}", JSON.toJSONString(responseWrapper), Thread.currentThread().getName() + num);

        try {
            System.out.println(Thread.currentThread().getName() + "开始运行");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "结束运行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}