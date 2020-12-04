/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author xuleyan
 * @version FileWriterTest.java, v 0.1 2020-11-20 9:47 上午
 */
public class FileWriterTest {

    private Object lock;

    
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("target/threads/test.txt"));
            fileWriter.write("test");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }
    }
}