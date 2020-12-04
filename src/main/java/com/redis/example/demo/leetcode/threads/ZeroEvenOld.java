/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.leetcode.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

/**
 *
 * @author xuleyan
 * @version ZeroEvenOld.java, v 0.1 2020-10-27 8:19 下午
 */
class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    CyclicBarrier lock = new CyclicBarrier(3);

    volatile int flag = 0;

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (flag == 0 ){
                printNumber.accept(0);
                flag += 1;
                lock.wait();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            while (flag != 1) {
                if (i / 2 == 0) {
                    printNumber.accept(1);
                }
                flag += 1;
                try {
                    lock.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            while (flag != 2) {
                if (i / 2 != 0) {
                    printNumber.accept(i);
                }
                flag = 0;
                try {
                    lock.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void main(String[] args) {
//        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
//        zeroEvenOdd.zero(2);
//    }
}