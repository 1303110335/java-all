package com.redis.example.demo.fastjson; /**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.redis.example.demo.domain.User;

/**
 * 测试fastjson的值过滤器
 * @author xuleyan
 * @version TestSerializer.java, v 0.1 2021-04-11 3:52 下午
 */
public class TestSerializer {

    public static void main(String[] args) {
        User user = new User();
        user.setName("haha");
        user.setAge(18);
        user.setMobile("18367829627");
        System.out.println(JSON.toJSONString(user, new SimpleValueFilter()));
    }

}