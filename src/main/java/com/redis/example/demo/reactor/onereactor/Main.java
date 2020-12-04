/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.reactor.onereactor;

/**
 *
 * @author xuleyan
 * @version Main.java, v 0.1 2020-09-29 5:40 下午
 */
public class Main {
    /**
     * 测试方法 ：telent localhost 9090
     * @param args
     */
    public static void main(String[] args) {
        Reactor reactor = new Reactor(9090);
        reactor.run();
    }
}
