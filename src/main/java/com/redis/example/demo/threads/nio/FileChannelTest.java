/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads.nio;

import org.springframework.util.StopWatch;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xuleyan
 * @version FileChannelTest.java, v 0.1 2020-11-19 11:19 上午
 */
public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        File fromFile = new File("/Users/xuleyan/learn/interview/interview.pdf");
        File toFile = new File("to.pdf");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 13ms
        fileCopyNormal(fromFile, toFile);
        // 25ms
//        fileCopyWithFileChannel(fromFile, toFile);
        //boolean delete = toFile.delete();

        stopWatch.stop();
        System.out.println("use time :" + stopWatch.getTotalTimeMillis());
    }


    public void testFileChannel() throws IOException {
        RandomAccessFile file = new RandomAccessFile("target/threads/test.txt", "rw");
        FileChannel channel = file.getChannel();
        String newData = "New String to write to file..." + System.currentTimeMillis() + "\n\r";

        int position = 0;
        for (int i = 0; i < 100; i++) {
            channel.position(position);
            byte[] data = newData.getBytes();
            channel.write(ByteBuffer.wrap(data));
            position += data.length;
        }

        // channel.force(true);
        channel.close();
    }

    /**
     * 普通的文件复制方法
     *
     * @param fromFile 源文件
     * @param toFile   目标文件
     * @throws FileNotFoundException 未找到文件异常
     */
    public static void fileCopyNormal(File fromFile, File toFile) throws FileNotFoundException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(fromFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(toFile));
            byte[] bytes = new byte[1024];
            int i;
            //读取到输入流数据，然后写入到输出流中去，实现复制
            while ((i = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用filechannel进行文件复制
     *
     * @param fromFile 源文件
     * @param toFile   目标文件
     */
    public static void fileCopyWithFileChannel(File fromFile, File toFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannelInput = null;
        FileChannel fileChannelOutput = null;
        try {
            fileInputStream = new FileInputStream(fromFile);
            fileOutputStream = new FileOutputStream(toFile);
            //得到fileInputStream的文件通道
            fileChannelInput = fileInputStream.getChannel();
            //得到fileOutputStream的文件通道
            fileChannelOutput = fileOutputStream.getChannel();
            //将fileChannelInput通道的数据，写入到fileChannelOutput通道
            fileChannelInput.transferTo(0, fileChannelInput.size(), fileChannelOutput);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (fileChannelInput != null)
                    fileChannelInput.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (fileChannelOutput != null)
                    fileChannelOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}