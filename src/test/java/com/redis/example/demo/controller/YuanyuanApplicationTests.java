/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.controller;

import com.redis.example.demo.domain.test.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
@RunWith(SpringRunner.class)
@SpringBootTest
public class YuanyuanApplicationTests {
    /**
     * 因为 POJO类 使用了 Component 注解，就是 Spring 一个组件，交由了 Spring 容器注入值
     * 所以使用 @Autowired 或者 @Resource，DI 注入在取值即可
     */
    @Resource
    private User user;
    @Test
    public void contextLoads() {
        System.out.println("------------------------------********************--------------------------------------------");
        System.out.println("·······················" + user);
    }
}