/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.dynamicproxy.javassist;

/**
 *
 * @author xuleyan
 * @version TicketService.java, v 0.1 2021-07-18 10:39 下午
 */
public interface TicketService {

    //售票
    public void sellTicket();

    //问询
    public void inquire();

    //退票
    public void withdraw();

}