/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo;

import com.redis.example.demo.importbean.HelloConfiguration;
import com.redis.example.demo.importbean.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 *
 * @author xuleyan
 * @version DemoApplicationTest2.java, v 0.1 2021-04-12 10:33 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {HelloConfiguration.class}) //表示只需要这一个文件
public class DemoApplicationTest2 {
    @Resource
    HelloService helloService;

    /**
     * @Author haien
     * @Description 扫描到helloService
     * @Date 2019/6/11
     * @Param []
     * @return void
     **/
    @Test
    public void contextLoads(){
        System.out.println(helloService.getClass());
    }
}