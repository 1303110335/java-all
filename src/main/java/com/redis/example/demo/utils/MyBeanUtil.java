/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.util.Assert;

/**
 *
 * @author xuleyan
 * @version MyBeanUtil.java, v 0.1 2020-09-16 3:03 下午
 */
public class MyBeanUtil {

    public static void copyProperties(Object source, Object target) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        //actualEditable.getDeclaredMethods()

    }


}