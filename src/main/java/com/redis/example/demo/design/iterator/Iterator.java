/**
 * bianque.com
 * Copyright (C) 2013-2020All Rights Reserved.
 */
package com.redis.example.demo.design.iterator;

/**
 *
 * @author xuleyan
 * @version Iterator.java, v 0.1 2020-10-22 2:53 下午
 */
public interface Iterator<Integer> {

    boolean hasNext();
    Integer next();
}