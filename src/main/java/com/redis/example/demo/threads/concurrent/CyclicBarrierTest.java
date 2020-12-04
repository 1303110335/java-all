/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xuleyan
 * @version CyclicBarrierTest.java, v 0.1 2020-11-20 10:36 上午
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 1; i <= barrier.getParties(); i++) {
            new Thread(new MyRunnable(barrier), "队友" + i).start();
        }
        System.out.println("main function is finished.");
    }

    private static class MyRunnable implements Runnable {

        private CyclicBarrier barrier;

        public MyRunnable(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 10; i++) {
                    Random random = new Random();
                    int randomInt = random.nextInt(2001) + 1000;
                    Thread.sleep(randomInt);
                    System.out.println(Thread.currentThread().getName() + ", 通过了第" + i + "个障碍物, 使用了 " + ((double) randomInt / 1000) + "s");
                    System.out.println(Thread.currentThread().getName() + ", previous : parties: " + this.barrier.getParties() + ", numberWating: " + this.barrier.getNumberWaiting());
                    this.barrier.await();
                    //System.out.println(Thread.currentThread().getName() + ", after : parties: " + this.barrier.getParties() + ", numberWating: " + this.barrier.getNumberWaiting());

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}