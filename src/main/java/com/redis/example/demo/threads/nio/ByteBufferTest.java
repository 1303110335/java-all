/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads.nio;


import java.nio.ByteBuffer;

/**
 *
 * @author xuleyan
 * @version ByteBufferTest.java, v 0.1 2020-11-19 11:33 上午
 */
public class ByteBufferTest {

    public static void main(String[] args) {

        System.out.println("----------- Test allocate ---------------");
        System.out.println("before allocate :" + Runtime.getRuntime().freeMemory());

        ByteBuffer buffer = ByteBuffer.allocate(102400);
        System.out.println("buffer=" + buffer);
        System.out.println("after allocate :" + Runtime.getRuntime().freeMemory());

        // 这部分直接用的系统内存，所以对JVM的内存没有影响
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);
        System.out.println("directBuffer = " + directBuffer);
        System.out.println("after direct alocate:" + Runtime.getRuntime().freeMemory());

        System.out.println("----------Test wrap--------");
        byte[] bytes = new byte[32];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer);

        buffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(buffer);
    }
}