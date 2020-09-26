/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.visitor2;

/**
 *
 * @author xuleyan
 * @version Visitor.java, v 0.1 2020-09-04 5:17 下午
 */
public interface Visitor {

    /**
     * 访问工程师类型
     * @param engineer
     */
    void visit(Engineer engineer);

    /**
     * 访问经理类型
     * @param manager
     */
    void visit(Manager manager);
}