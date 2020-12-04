/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads;

import com.redis.example.demo.druid.dao.impl.AccountDAOImpl;
import com.redis.example.demo.druid.domain.Account;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author xuleyan
 * @version MyThread.java, v 0.1 2020-09-25 2:00 下午
 */
@Slf4j
public class MyThread extends ServiceThread {

    private Integer num;

    public MyThread(Integer num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.waitForRunning(3000);

                System.out.println(Thread.currentThread().getName() + "开始执行");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +"执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread(1);
        Thread thread = new Thread(myThread);
        thread.start();

        System.out.println("主线程睡眠开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程睡眠结束");
        myThread.wakeup();

    }
}