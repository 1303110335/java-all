/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.threads.threadLocal;

import com.redis.example.demo.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * -Xms128m -Xmx128m -Xmn64M -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/xuleyan/code/redis/src/main/java/com/redis/example/demo/threads/threadLocal -verbose:gc -Xloggc:gc.log -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintReferenceGC
 *
 * @author xuleyan
 * @version TestThreadLocalOom.java, v 0.1 2021-07-30 8:36 下午
 */
public class TestThreadLocalOom {

    public static final Integer BIG_LOOP = 10000;

    public static ThreadLocal<List<User>> threadLocal = new InheritableThreadLocal<>();
//    public static ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 500; i ++) {
            executorService.execute(() -> {
//                ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();
                threadLocal.set(new TestThreadLocalOom().addBigList());
                System.out.println("ThreadId:" + Thread.currentThread().getName() + "addBigList");
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                threadLocal.remove();
            });
        }

        Thread.sleep(10000);
        executorService.shutdown();
    }

    public List<User> addBigList() {
        List<User> params = new ArrayList<>(BIG_LOOP);
        for (int i = 0; i<BIG_LOOP; i++) {
            params.add(new User("haha", 28));
        }
        return params;
    }
}