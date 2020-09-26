/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.controller;

import com.redis.example.demo.domain.MyItem;
import com.redis.example.demo.domain.User;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms256M -Xmx256M -Xmn128m -XX:MaxMetaspaceSize=128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/xuleyan/outMemoryTest.hprof
 * @author xuleyan
 * @version TestOutMemory.java, v 0.1 2020-09-02 4:40 下午
 */
public class TestOutMemory {

    public static void main(String[] args) throws InterruptedException {
        MyItem myItem = new MyItem();
        User user = new User("lele", 18);
//        String name = "lele";
//        char c = 'l';
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
    }


    /**
     * 栈深度不足溢出
     */
    public static void testStackOverFlow() {
        while(true){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(60*60*1000);
                    } catch(InterruptedException e) { }
                }
            }).start();
        }
    }

    /**
     * 对内存不足溢出
     */
    public static void testOutOfMemory() {
        List<User> users = new ArrayList<>();
        while (true) {
            User user = new User("lele", 18);
            users.add(user);
            System.out.println(users.size());
        }
    }
}