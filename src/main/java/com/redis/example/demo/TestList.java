/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo;

import com.redis.example.demo.domain.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version TestList.java, v 0.1 2021-08-04 7:44 下午
 */
public class TestList {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();

        // id 为null会报空指针
        User user2= new User();
        user2.setName("11");
        user2.setId(null);
        userList.add(user2);

        User user= new User();
        user.setName("11");
        user.setId(1);
        userList.add(user);



        userList.sort(Comparator.comparing(User::getId));
        System.out.println(userList);
    }
}