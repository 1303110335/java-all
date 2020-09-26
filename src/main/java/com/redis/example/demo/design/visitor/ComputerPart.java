/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.visitor;

/**
 *
 * @author xuleyan
 * @version ComputerPart.java, v 0.1 2020-09-04 4:49 下午
 */
public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}