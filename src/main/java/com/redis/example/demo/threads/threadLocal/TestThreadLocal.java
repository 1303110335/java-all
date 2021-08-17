/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.threads.threadLocal;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * 每个线程都有一个ThreadLocalMap, 该ThreadLocalMap 中有许多entry,每个entry的key就是当前的threadLocal的弱引用,value是填入的值
 * 当系统发生gc的时候，当没有地方强引用该threadLocal，那么这个弱引用的key就会被回收，
 * 但是这个entry仍旧被threadLocalMap强引用，threadLocalMap被当前线程强引用，因此无法回收，导致内存泄露
 * 所以每次用完threadLocal之后都需要去remove它，并且threadLocal在set,get,remove的时候会清除key为null的value值
 * @author xuleyan
 * @version TestThreadLocal.java, v 0.1 2021-07-02 10:21 上午
 */
public class TestThreadLocal {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> test("abc", false));
        t.start();
        t.join();
        System.out.println("----gc后----");
        Thread t2 = new Thread(() -> test("def", true));
        t2.start();
        t2.join();
    }

    @SneakyThrows
    private static void test(String s, boolean isGc) {
        new ThreadLocal<>().set(s);
//        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
//        objectThreadLocal.set(s);
//        objectThreadLocal.remove();

        if (isGc) {
            System.gc();
        }

        Thread t = Thread.currentThread();
        Class<? extends Thread> clz = t.getClass();
        Field field = clz.getDeclaredField("threadLocals");
        field.setAccessible(true);
        Object threadLocalMap = field.get(t);
        Class<?> tlmClass = threadLocalMap.getClass();
        Field tableField = tlmClass.getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] arr = (Object[]) tableField.get(threadLocalMap);
        for (Object o : arr) {
            if (o != null) {
                Class<?> entryClass = o.getClass();
                Field valueField = entryClass.getDeclaredField("value");
                Field referentField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                valueField.setAccessible(true);
                referentField.setAccessible(true);

                Object value = valueField.get(o);
                Object referent = referentField.get(o);
                System.out.println(String.format("弱引用:%s, 值:%s", referent, value));
            }
        }
    }


}