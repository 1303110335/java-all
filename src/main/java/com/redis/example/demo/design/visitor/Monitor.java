/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.visitor;

/**
 * 显示器
 * @author xuleyan
 * @version Monitor.java, v 0.1 2020-09-04 4:50 下午
 */
public class Monitor  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}