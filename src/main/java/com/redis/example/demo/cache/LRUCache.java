/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.cache;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LRU缓存
 * @author xuleyan
 * @version LRUCache.java, v 0.1 2021-06-28 2:12 下午
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;
    public LRUCache(int initialCapacity) {
        super(initialCapacity, 0.75f, true);
        this.capacity = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<>(10);
        for (int i = 0; i < 10; i++) {
            lruCache.put(i + "", "value" + i);
        }

        System.out.println(lruCache);

        lruCache.get("1");
        lruCache.get("2");
        System.out.println(lruCache);

        lruCache.put("11", "value11");
        System.out.println(lruCache);
    }
}