/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads.alive;

/**
 *
 * @author xuleyan
 * @version IsAliveTest.java, v 0.1 2020-11-20 11:24 上午
 */
public class IsAliveTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ThreadDemo());
        t.start();
        t.join();
        System.out.println("thread t status = " + t.isAlive());
        System.out.println("thread main status = " + Thread.currentThread().isAlive());
    }

}

class ThreadDemo implements Runnable {

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + "线程状态:" + t.isAlive());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}