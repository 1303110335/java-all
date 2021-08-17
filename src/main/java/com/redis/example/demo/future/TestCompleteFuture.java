/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.future;


import java.util.concurrent.CompletableFuture;

/**
 *
 * @author xuleyan
 * @version TestCompleteFuture.java, v 0.1 2021-06-29 5:06 下午
 */
public class TestCompleteFuture {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
                System.out.println("do supply:" + Thread.currentThread().getName());
                System.out.println("this is first task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "first";
        });

        // 将主线程阻塞住
        future.join();
        future.whenComplete((s, e) -> {
            try {
                System.out.println("whenComplete:" + Thread.currentThread().getName());
                Thread.sleep(100);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("正确完成输出结果:" + s + ":" + Thread.currentThread().getName());
            System.out.println("e=" + e);
        });

        String now = future.getNow("now");
        System.out.println("now:" + now + ":" + Thread.currentThread().getName());
        Thread.sleep(2000);
    }
}