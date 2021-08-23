/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.guava.collections;

import com.alibaba.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version TestLists.java, v 0.1 2021-08-23 4:45 下午
 */
public class TestLists {

    public static void main(String[] args) {
        List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");
        System.out.println(theseElements);

        ArrayList<Integer> list = Lists.newArrayListWithExpectedSize(100);
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println(list);

        HashSet<String> objects = Sets.newHashSetWithExpectedSize(100);
        HashMap<String, String> hashMap = Maps.newHashMapWithExpectedSize(100);
    }
}