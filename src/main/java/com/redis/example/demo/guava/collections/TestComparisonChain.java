/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.guava.collections;

import com.redis.example.demo.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version TestComparisonChain.java, v 0.1 2021-08-23 3:38 下午
 */
public class TestComparisonChain {

    public static void main(String[] args) throws CloneNotSupportedException {
        List<User> userList = new ArrayList<>();

        // id 为null会报空指针
        User user2= new User();
        user2.setName("11");
        user2.setId(2);
        user2.setAge(10);
        userList.add(user2);

        User user= new User();
        user.setName("11");
        user.setId(1);
        user.setAge(9);
        userList.add(user);

        User cloneUser = (User) user.clone();
        cloneUser.setId(3);
        userList.add(cloneUser);

        Collections.sort(userList);
        //        userList.sort(Comparator.comparing(User::getId));
        System.out.println(userList);
    }
}