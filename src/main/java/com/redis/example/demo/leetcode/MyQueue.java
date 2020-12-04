/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.leetcode;

import java.util.Stack;

/**
 * 用两个堆栈实现队列的功能
 *输入：
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 1, 1, false]
 *
 * 解释：
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 * @author xuleyan
 * @version MyQueue.java, v 0.1 2020-11-17 6:02 下午
 */
public class MyQueue {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek());; // return 1
        System.out.println(myQueue.pop());; // return 1, queue is [2]
        System.out.println(myQueue.empty());; // return false
    }

    private Stack<Integer> s1;
    private Stack<Integer> s2;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    private int front;

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (s1.empty()) {
            front = x;
        }
        // 1.若s1不为空，先把s1的数据push到s2
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        // 2.再push x 到s2
        s2.push(x);
        // 3.再将s2 push到s1
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        Integer pop = s1.pop();
        if (!s1.empty()) {
            front = s1.peek();
        }
        return pop;
    }

    /** Get the front element. */
    public int peek() {
        return front;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty();
    }

}