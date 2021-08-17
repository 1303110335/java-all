/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @author xuleyan
 * @version TestExecutors.java, v 0.1 2021-07-26 4:06 下午
 */
public class TestExecutors {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testCachedPool();
    }

    private static void testCachedPool() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> submit = executorService.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    return "callable " + Thread.currentThread().getName();
                }
            });
            futureList.add(submit);
        }
        futureList.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}