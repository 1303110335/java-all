/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.guava.basic;

import com.google.common.base.Optional;
import com.redis.example.demo.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version TestNull.java, v 0.1 2021-08-23 11:33 上午
 */
public class TestNull {

    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent());
        System.out.println(possible.get());


    }
}