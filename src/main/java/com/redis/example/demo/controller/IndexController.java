/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.xuleyan.frame.extend.redis.jedis.JedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.tags.Param;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuleyan
 * @version IndexController.java, v 0.1 2020-08-01 5:07 下午
 */
@RestController
@RequestMapping(value = "/test")
public class IndexController {

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

    @RequestMapping("/test")
    public String test() {
        List<String> nameList = Arrays.asList("haha", "lele");
        return JSON.toJSONString(nameList);
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