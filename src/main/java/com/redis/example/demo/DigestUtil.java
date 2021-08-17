/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author xuleyan
 * @version DigestUtil.java, v 0.1 2021-04-07 4:52 下午
 */
public class DigestUtil {

    final static char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] args) {
//        String s = MD5("/Users/xuleyan/code/redis/src/main/resources/db.properties");
        String s = SHA1("/Users/xuleyan/code/redis/src/main/resources/db.properties");
        System.out.println(s);
    }

    /**
     * 对文件进行MD5摘要
     * @param path
     * @return
     */
    public static String MD5(String path) {
        String pathName = path;
        String md5 = "";

        try {
            File file = new File(pathName);
            FileInputStream ins = new FileInputStream(file);
            FileChannel ch = ins.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(byteBuffer);
            ins.close();
            md5 = toHexString(md.digest());
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static String SHA1(String path) {
        String pathName = path;
        String sha1 = "";

        try {
            File file = new File(pathName);
            FileInputStream ins = new FileInputStream(file);
            FileChannel ch = ins.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(byteBuffer);
            ins.close();
            sha1 = toHexString(md.digest());
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    /**
     * 转16进制
     * @param tmp
     * @return
     */
    private static String toHexString(byte[] tmp) {
        String s;
        char str[] = new char[tmp.length * 2];
        int k = 0;
        for (int i = 0; i < tmp.length; i++) {
            byte byte0 = tmp[i];
            str[k++] = hex[byte0 >>> 4 & 0xf];
            str[k++] = hex[byte0 & 0xf];
        }
        s = new String(str);
        return s;
    }
}