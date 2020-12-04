/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.utils;

import java.io.File;
import java.math.BigDecimal;

/**
 *
 * @author xuleyan
 * @version DiskUtil.java, v 0.1 2020-11-19 10:27 下午
 */
public class DiskUtil {

    public static void main(String[] args) {
        File file = new File("/Users/xuleyan/code/redis");
        long totalSpace = file.getTotalSpace();
        System.out.println(divide(totalSpace));
        long freeSpace = file.getFreeSpace();
        System.out.println(divide(freeSpace));
    }

    public static BigDecimal divide(long totalSpace) {
        return new BigDecimal("" + totalSpace).divide(new BigDecimal((1024*1024*1024) + ""), 2,BigDecimal.ROUND_HALF_UP);
    }
}