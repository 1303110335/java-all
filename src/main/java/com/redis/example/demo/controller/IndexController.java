/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.redis.example.demo.annations.EncryptAndDecryptBody;
import com.redis.example.demo.cache.TestCacheAble;
import com.redis.example.demo.druid.dao.impl.AccountDAOImpl;
import com.redis.example.demo.druid.domain.Account;
import com.redis.example.demo.threads.MyThread;
import com.xuleyan.frame.extend.redis.jedis.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.tags.Param;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuleyan
 * @version IndexController.java, v 0.1 2020-08-01 5:07 下午
 */
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class IndexController {

    @Autowired
    private TestCacheAble testCacheAble;

    @Resource
    private Executor testExecutor;

    @RequestMapping("/test-log")
    public void testLog() {
        log.info("this is info");
        log.warn("this is warn");
        log.error("this is err");
        log.debug("this is debug");
        System.out.println("log finish");
    }

    /**
     * 1s
     */
    @RequestMapping("/test-thread")
    public void testExecutor() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 1; i <= 1000; i++) {
            testExecutor.execute(new MyThread(i));
        }
        stopWatch.stop();
        log.info("耗时：{}ms", stopWatch.getTotalTimeMillis());
    }

    /**
     * 2.5s
     */
    @RequestMapping("/test1000")
    public void test1000() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 1; i <= 1000; i++) {
            String name = "吴洋" + i;
            new AccountDAOImpl().save(new Account(null, name, 25, "1412341234", "江西", 0));
        }
        stopWatch.stop();
        log.info("耗时：{}ms", stopWatch.getTotalTimeMillis());
    }

    @Resource(name = "jedisTemplate")
    private JedisTemplate jedisTemplate;

    @RequestMapping("/index")
    public String index() {
        String set = jedisTemplate.set("name", "xly2");
        System.out.println(set);
        String name = jedisTemplate.get("name");
        System.out.println(name);

        return "index";
    }

    @RequestMapping("/test/{name}")
    public String testName(@PathVariable String name) {
//        List<String> nameList = Arrays.asList("haha", "lele");
//        return JSON.toJSONString(nameList);
        return testCacheAble.testCache(name);
    }

    @RequestMapping("/del/{name}")
    public void delName(@PathVariable String name) {
        testCacheAble.delCache(name);
    }

    @RequestMapping("/xss2")
    public String testXss() {
        return "<script>for(var i=0;i<2;i++){alert(\"弹死你\"+i);}</script>";
    }

    @PostMapping(value = "/xss")
    public Object test(String name) {
        System.out.println(name);
        return name;
    }

    @PostMapping(value = "/json")
    public Object testJSON(@RequestBody Param param) {
        return param;
    }

    @PostMapping(value = "/jsondecry")
    @EncryptAndDecryptBody
    public Object testJSONDecry(@RequestBody Param param) {
        return param;
    }

    @GetMapping(value = "/query")
    public Object testQuery(String q) {
        return q;
    }

    @PostMapping(value = "/upload")
    public Object upload(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return "OK";
    }
}