/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo;

import java.io.File;
import java.math.BigDecimal;

/**
 *
 * @author xuleyan
 * @version TestSize.java, v 0.1 2021-04-01 4:09 下午
 */
public class TestSize {

    public static void main(String[] args) {

//        File file = new File("out.pdf");
//        double fileSize = file.length();
//        BigDecimal bigDecimal = new BigDecimal(fileSize/1024/1024).setScale(8, BigDecimal.ROUND_HALF_UP);
//        System.out.println(bigDecimal.toPlainString());

        try {
            int number = 0;
            int i = 5 / number;
            System.out.println(i);
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }
}