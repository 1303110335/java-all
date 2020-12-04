/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.reactor.multireactor;

/**
 *
 * @author xuleyan
 * @version Main.java, v 0.1 2020-09-29 7:40 下午
 */
public class Main {
    public static void main(String[] args) {
        Reactor reactor = new Reactor(9090);
        reactor.run();
    }
}