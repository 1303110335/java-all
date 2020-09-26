/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.controller;

import com.beust.jcommander.internal.Lists;
import com.google.common.base.*;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author xuleyan
 * @version TestGuava.java, v 0.1 2020-09-03 4:33 下午
 */
public class TestGuava {

    public static void main(String[] args) {
        testFile();


    }

    public static void testFile() {
        File file = new File("src/main/java/com/redis/example/demo/controller/firstword2.txt");
        List<String> list = Lists.newArrayList();
        try {
            list = Files.readLines(file, Charsets.UTF_8);
            list.forEach(item -> {
                System.out.println("item :" + item);
                List<String> nameList = Splitter.on(",").omitEmptyStrings().splitToList(item);
                System.out.println("nameList :" + nameList);
                System.out.println("nameList.size :" + nameList.size());
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void spendTime() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        for(int i=0; i<100000; i++){
            System.out.println(1);
        }
        long ms = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(ms);
    }

    /**
     * 根据正则进行分割，并去除空串和空格
     */
    public static void splitStrOnPattern() {
        String input = "aa. dd,,ff,,.";
        List<String> result = Splitter.onPattern("[.|, ]").omitEmptyStrings().splitToList(input);
        System.out.println(result);
    }

    public static void testStrToMap() {
        String str = "xiaoming=11,xiaohong=23";
        Map<String,String> map = Splitter.on(",").withKeyValueSeparator("=").split(str);
        System.out.println(map);

    }

    public static void testMapToStr() {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("xiaoming", 12);
        map.put("xiaohong",13);
        String result = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(result);
    }

    public static void testStrToList() {
        String str = "1-2-3-4-  5-  6   ";
        List<String> list = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);
        System.out.println(list);
    }
}