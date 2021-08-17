/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.threads.callablefolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @author xuleyan
 * @version TestCallable.java, v 0.1 2021-07-26 3:35 下午
 */
public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<String> c = new MyCallable(i + "");
            Future<String> future = executorService.submit(c);
            futures.add(future);
        }


        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }
}