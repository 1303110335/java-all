/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.queue;

import com.redis.example.demo.domain.User;

import java.util.PriorityQueue;

/**
 *
 * @author xuleyan
 * @version TestPriorityQueue.java, v 0.1 2021-08-24 2:41 下午
 */
public class TestPriorityQueue {

    public static void main(String[] args) throws CloneNotSupportedException {
        // 最小堆实现的优先队列
        // 对象需要实现Comparable 接口
        PriorityQueue<User> priorityQueue = new PriorityQueue<>();
        User user = new User();
        user.setAge(10);
        user.setId(2);
        priorityQueue.offer(user);
        User userClone1 = (User) user.clone();
        userClone1.setAge(10);
        userClone1.setId(1);
        priorityQueue.offer(userClone1);
        User userClone2 = (User) user.clone();
        userClone2.setAge(90);
        priorityQueue.offer(userClone2);
        System.out.println(priorityQueue.peek());
    }
}