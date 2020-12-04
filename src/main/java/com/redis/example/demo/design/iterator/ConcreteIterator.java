/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.iterator;

/**
 * @author xuleyan
 * @version ConcreteIterator.java, v 0.1 2020-10-22 2:55 下午
 */
public class ConcreteIterator<Integer> implements Iterator {

    private Integer[] elements;
    private int position = 0;

    public ConcreteIterator(Integer[] elements) {
        this.elements = elements;
    }

    @Override
    public boolean hasNext() {
        return position < elements.length;
    }

    @Override
    public Object next() {
        return elements[position ++];
    }
}