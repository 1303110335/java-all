/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.visitor2;

/**
 *
 * @author xuleyan
 * @version Client.java, v 0.1 2020-09-04 5:18 下午
 */
public class Client {

    public static void main(String[] args) {
        // 构建报表
        BusinessReport report = new BusinessReport();
        System.out.println("=========== CEO看报表 ===========");
        report.showReport(new CEOVisitor());
        System.out.println("=========== CTO看报表 ===========");
        report.showReport(new CTOVisitor());
    }
}