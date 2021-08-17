/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.heap;

import org.ehcache.xml.model.Heap;

import java.util.ArrayList;

/**
 * 堆溢出
 *
 * @author xuleyan
 * @version HeapTest.java, v 0.1 2021-07-06 2:23 下午
 */
public class HeapTest {

    public static void main(String[] args) {

        ArrayList<Heap> list = new ArrayList<>();

        while (true) {
            list.add(new Heap());
        }

    }
}