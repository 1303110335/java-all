/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.iterator;

/**
 *
 * @author xuleyan
 * @version ConcreteAggregate.java, v 0.1 2020-10-22 2:54 下午
 */
public class ConcreteAggregate implements Aggregate {

    private Integer[] elements;

    public ConcreteAggregate() {
        elements = new Integer[10];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = i;
        }
    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(elements);
    }
}