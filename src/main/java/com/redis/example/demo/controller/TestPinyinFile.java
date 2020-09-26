/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.controller;

import java.io.*;

/**
 * 文件读取中文转化成update SQL
 * @author xuleyan
 * @version TestPinyinFile.java, v 0.1 2020-08-24 2:01 下午
 */
public class TestPinyinFile {

    public static void main(String[] args) throws IOException {
        testFile();
    }

    private static void testFile() throws IOException {
        File file = new File("/Users/xuleyan/code/redis/src/main/java/com/redis/example/demo/controller/firstword.txt");
        InputStream in = null;
        File writeFile = new File("/Users/xuleyan/code/redis/src/main/java/com/redis/example/demo/controller/out.txt");
        if (!writeFile.exists()) {
            writeFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(writeFile, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


        StringBuffer sb = new StringBuffer();
        if (file.exists()) {
            byte[] temp = new byte[1024];
            int byteread = 0;
            in = new FileInputStream(file);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(temp)) != -1) {
                String str = new String(temp, 0, byteread);
                sb.append(str);
            }
            String[] split = sb.toString().split(",");
            TestPinyin pinYinUtil = new TestPinyin();
            String format = "UPDATE bqhealth_cloud_cdata_drug_list_new SET first_word = %s WHERE drug_general_purpose_name = %s;";
            for (String name : split) {
                String result = String.format(format, pinYinUtil.getPinYinHeadChar(name), name);
                bufferedWriter.write(result + "\n");
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
    }
}