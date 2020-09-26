/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads;

import com.redis.example.demo.druid.dao.impl.AccountDAOImpl;
import com.redis.example.demo.druid.domain.Account;

/**
 *
 * @author xuleyan
 * @version MyThread.java, v 0.1 2020-09-25 2:00 下午
 */
public class MyThread implements Runnable {

    private Integer num;

    public MyThread(Integer num) {
        this.num = num;
    }

    @Override
    public void run() {
        new AccountDAOImpl().save(new Account(null, "吴洋" + num, 25, "1412341234", "江西"));
    }
}