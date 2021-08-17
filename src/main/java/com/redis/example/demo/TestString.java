/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo;

import java.lang.reflect.Field;

/**
 *
 * @author xuleyan
 * @version TestString.java, v 0.1 2021-08-01 8:11 上午
 */
public class TestString {

    public static void main(String[] args) {
//        try {
//            String s = new String("123");
//            Field value = s.getClass().getDeclaredField("value");
//            value.setAccessible(true);
//            value.set(s, "abcd".toCharArray());
//            System.out.println(s);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }


        String s = new String("123");
        System.out.println(s.hashCode());

        String s1 =  "123";
        System.out.println(s1.hashCode());
        System.out.println(s1 == s);

        String s2 = s.intern();
        System.out.println(s2.hashCode());
        System.out.println(s2 == s1);
    }
}