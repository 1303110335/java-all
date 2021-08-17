/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.service;

import com.redis.example.demo.es.HsbLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 *
 * @author xuleyan
 * @version TestHsbLog.java, v 0.1 2021-05-11 8:35 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHsbLog {

    @Resource
    private HsbLogService hsbLogService;

    @Test
    public void test() {
        hsbLogService.runLog();
    }
}