/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.websocket;

import com.redis.example.demo.druid.dao.impl.AccountDAOImpl;
import com.redis.example.demo.druid.domain.Account;
import lombok.SneakyThrows;

import java.util.List;

/**
 * @author xuleyan
 * @version MyThread.java, v 0.1 2020-12-21 4:14 下午
 */
public class MyThread implements Runnable {
    private Integer sum;
    private volatile boolean stopMe = false;

    @SneakyThrows
    @Override
    public void run() {

        WebSocketServlet wbs = new WebSocketServlet();
        while (!stopMe) {
            List<Account> list = new AccountDAOImpl().list();
            boolean send = false;
            if (sum == null) {
                System.out.println("first change");
                sum = list.size();
                send = true;
            } else if (sum != list.size()) {
                System.out.println("change");
                send = true;
                sum = list.size();

            }

            if (send) {
                wbs.onMessage(sum);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void stopMe() {
        stopMe = true;
    }
}