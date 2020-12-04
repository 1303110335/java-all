/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author xuleyan
 * @version TestCacheAble.java, v 0.1 2020-10-15 5:37 下午
 */
@Service
@Slf4j
public class TestCacheAble {

    @Cacheable(value = "test-cache", key = "#name")
    public String testCache(String name) {
        log.info("name = {}", name);
        String result = "test";
        return result;
    }

    @CacheEvict(value = "test-cache", key = "#name")
    public void delCache(String name) {
        log.info("delCache = {}", name);
    }
}