/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.test;

import java.util.Random;

/**
 *
 * @author xuleyan
 * @version JoinDemo.java, v 0.1 2020-11-07 2:17 下午
 */
public class JoinDemo extends Thread{

    private final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    int i;
    Thread previousThread; //上一个线程
    public JoinDemo(Thread previousThread,int i){
        this.previousThread=previousThread;
        this.i=i;
//        threadLocal.set(i);
//        System.out.println("set i = " + i + ", currentThread = " + Thread.currentThread());
    }

    @Override
    public void run() {
//        Random random = new Random();
//        int i = random.nextInt(1000);
//        try {
//            Thread.sleep(i);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            //调用上一个线程的join方法，大家可以自己演示的时候可以把这行代码注释掉
            previousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num:"+ this.i);

//        System.out.println("get i = " + threadLocal.get() + ", currentThread = " + Thread.currentThread());
    }


    public static void main(String[] args) throws InterruptedException {

        Thread previousThread=Thread.currentThread();
        for(int i=0;i<10;i++){
            JoinDemo joinDemo=new JoinDemo(previousThread,i);
            joinDemo.start();
            //threadLocal.get();
            previousThread=joinDemo;
        }
    }
}
