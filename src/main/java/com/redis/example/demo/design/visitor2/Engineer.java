/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.visitor2;

import java.util.Random;

/**
 * 工程师
 * @author xuleyan
 * @version Engineer.java, v 0.1 2020-09-04 5:16 下午
 */
public class Engineer extends Staff {

    public Engineer(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    // 工程师一年的代码数量
    public int getCodeLines() {
        return new Random().nextInt(10 * 10000);
    }
}