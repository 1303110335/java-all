/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.guava.string;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 *
 * @author xuleyan
 * @version TestJoin.java, v 0.1 2021-08-23 5:11 下午
 */
public class TestJoin {

    public static void main(String[] args) {
        Joiner joiner = Joiner.on(";").skipNulls();
        String join = joiner.join("xly", "wy", null, "haha");
        System.out.println(join);


        Iterable<String> split = Splitter.on(",").trimResults().omitEmptyStrings().split("foo,bar,,haha");
        System.out.println(split);

        String onlyDigit = CharMatcher.inRange('0', '9').retainFrom("xly27haha");
        String onlyLetters = CharMatcher.inRange('a', 'z').retainFrom("xly27haha");
        System.out.println(onlyDigit);
        System.out.println(onlyLetters);


    }
}