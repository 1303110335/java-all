/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.regex;

/**
 *
 * @author xuleyan
 * @version Test.java, v 0.1 2020-11-20 3:09 下午
 */
public class Test {

    public static void main(String[] args) {
        String subString = "a||b";
        String[] tags = subString.split("\\|\\|");
        for (String tag : tags) {
            System.out.println(tag);
        }
    }
}