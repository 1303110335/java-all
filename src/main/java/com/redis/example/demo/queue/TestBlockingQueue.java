/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * @author xuleyan
 * @version TestBlockingQueue.java, v 0.1 2020-12-20 7:36 下午
 */
public class TestBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        //testAdd();

        //testOffer();

        testPut();
    }

    // 将指定元素插
    //入此队列中，将等待可用的空间（如果有必要）
    private static void testPut() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque(2);
        blockingQueue.put("name1");
        blockingQueue.put("name2");
        // 超出长度会阻塞
        blockingQueue.put("name3");

//        blockingQueue.remove("name1");
        System.out.println(blockingQueue);
    }

    // 将指定元素插入此队列中（如果立即可行
    //且不会违反容量限制），成功时返回 true，如果当前没有可用的空间，则返回 false。
    private static void testOffer() {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque(2);
        System.out.println(blockingQueue.offer("name1"));
        System.out.println(blockingQueue.offer("name2"));
        System.out.println(blockingQueue.offer("name3"));
    }

    // 将指定元素插入此队列中（如果立即可行
    //且不会违反容量限制），成功时返回 true，如果当前没有可用的空间，则抛
    //出 IllegalStateException。如果该元素是 NULL，则会抛出 NullPointerException 异常。
    private static void testAdd() {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque(2);
        blockingQueue.add("name1");
        blockingQueue.add("name2");
        blockingQueue.add("name3");
    }
}