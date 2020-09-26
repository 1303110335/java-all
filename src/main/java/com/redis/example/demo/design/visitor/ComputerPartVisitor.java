/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.visitor;

/**
 * 访问者的接口
 * @author xuleyan
 * @version ComputerPartVisitor.java, v 0.1 2020-09-04 4:49 下午
 */
public interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}