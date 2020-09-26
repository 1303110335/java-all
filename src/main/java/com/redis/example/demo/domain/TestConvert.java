/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.domain;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xuleyan
 * @version TestConvert.java, v 0.1 2020-09-16 2:58 下午
 */
public class TestConvert {

    public static Map<String, String> fieldToFieldMap = new HashMap<>();

    static {
        fieldToFieldMap.put("name", "name2");
        fieldToFieldMap.put("age", "age2");
    }

    public static void main(String[] args) {
        User user = new User();
        user.setAge(10);
        user.setName("xly");

        String userJson = JSON.toJSONString(user);
        System.out.println("userJson:" + userJson);

        User user1 = JSON.parseObject(userJson, User.class);
        System.out.println("user1:" + user1);

        User2 user2 = JSON.parseObject(userJson, User2.class);
        System.out.println("user2:" + user2);
//        User2 user2 = new User2();
//        BeanUtils.copyProperties(user, user2);
//        System.out.println(JSON.toJSONString(user2));
    }

}