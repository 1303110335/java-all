/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.guava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;

/**
 *
 * @author xuleyan
 * @version TestImmutable.java, v 0.1 2021-08-23 3:58 下午
 */
public class TestImmutable {

    public static void main(String[] args) {

        ImmutableSortedSet<String> sortedSet = ImmutableSortedSet.of("b", "c", "d", "a");
        System.out.println(sortedSet);

        ImmutableList<String> of = ImmutableList.of("b", "c", "d", "a");
        System.out.println(of);
    }
}