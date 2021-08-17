/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author xuleyan
 * @version TestComplete.java, v 0.1 2021-08-15 10:36 下午
 */
public class TestComplete {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 睡眠2秒再返回结果
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        // 注意，此处如果睡眠3秒，调用complete时completableFuture已经执行完毕返回1，complete方法不会修改返回值。此时调用get方法返回1
        // 如果此处没有睡眠3秒，调用complete时completableFuture尚未执行完毕，下面调用get的时候方法返回1000
//        Thread.sleep(3000);
        completableFuture.complete(1000);

        Integer integer = completableFuture.get();

        System.out.println(integer);
    }
}