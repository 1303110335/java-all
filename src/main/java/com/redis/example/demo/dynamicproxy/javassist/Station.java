/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.dynamicproxy.javassist;

/**
 * 售票服务接口实现类，车站
 * @author xuleyan
 * @version Station.java, v 0.1 2021-07-18 10:39 下午
 */
public class Station implements TicketService {

    @Override
    public void sellTicket() {
        System.out.println("\n\t售票.....\n");
    }

    @Override
    public void inquire() {
        System.out.println("\n\t问询。。。。\n");
    }

    @Override
    public void withdraw() {
        System.out.println("\n\t退票......\n");
    }

}