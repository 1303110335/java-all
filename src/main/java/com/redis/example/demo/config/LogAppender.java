/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.config;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 *
 * @author xuleyan
 * @version LogAppender.java, v 0.1 2021-03-15 7:55 下午
 */
public class LogAppender extends DailyRollingFileAppender {

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        return this.getThreshold().equals(priority);
    }
}