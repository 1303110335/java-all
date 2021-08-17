/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author xuleyan
 * @version TestLog.java, v 0.1 2020-09-22 3:33 下午
 */
public class TestLog {
    private static final Logger log = LoggerFactory.getLogger(TestLog.class);

    public static void main(String[] args) {
        log.info("this is info");
        log.warn("this is warn");
        log.error("this is err");
        log.debug("this is debug");

    }
}