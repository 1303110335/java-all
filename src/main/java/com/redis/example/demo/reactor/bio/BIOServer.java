/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.reactor.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author xuleyan
 * @version BIOServer.java, v 0.1 2020-09-29 5:58 下午
 */
public class BIOServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9696);
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    byte[] byteRead = new byte[1024];
                    socket.getInputStream().read(byteRead);

                    String req = new String(byteRead, StandardCharsets.UTF_8);//encode
                    // do something

                    byte[] byteWrite = "Hello".getBytes(StandardCharsets.UTF_8);//decode
                    socket.getOutputStream().write(byteWrite);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}