/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.guava.basic;

import com.google.common.base.Objects;
import com.redis.example.demo.domain.test.User;

/**
 *
 * @author xuleyan
 * @version TestObjects.java, v 0.1 2021-08-23 1:40 下午
 */
public class TestObjects {

    public static void main(String[] args) {
        boolean equal = Objects.equal("1", null);
        System.out.println(equal);

        int hashCode = Objects.hashCode(new User());
        System.out.println(hashCode);

        int hashCode2 = Objects.hashCode(new User(), 2);
        System.out.println(hashCode2);
    }
}