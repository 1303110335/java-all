/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.visitor2;

import java.util.Random;

/**
 * 员工基类
 * @author xuleyan
 * @version Staff.java, v 0.1 2020-09-04 5:15 下午
 */
public abstract class Staff {

    public String name;
    /**
     * 员工KPI
     */
    public int kpi;

    public Staff(String name) {
        this.name = name;
        kpi = new Random().nextInt(10);
    }

    /**
     * 核心方法，接受Visitor的访问
     * @param visitor
     */
    public abstract void accept(Visitor visitor);
}