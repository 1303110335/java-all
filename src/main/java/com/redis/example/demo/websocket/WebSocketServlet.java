/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * springboot 整合 websocket
 * 参考 https://www.jb51.net/article/175159.htm
 * @author xuleyan
 * @version WebSocketServlet.java, v 0.1 2020-12-21 4:14 下午
 */
@ServerEndpoint("/websocket")
@Component
public class WebSocketServlet {

    MyThread thread1 = new MyThread();
    Thread thread = new Thread(thread1);

    // 用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArrayList<WebSocketServlet> webSocketSet = new CopyOnWriteArrayList<>();
    private Session session = null;

    /**
     * 开启连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println(webSocketSet);
        thread.start();
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        thread1.stopMe();
    }

    /**
     * 发送消息告知数据库发生变化
     * @param count
     */
    @OnMessage
    public void onMessage(Integer count) throws IOException {
        System.out.println("发生变化" + count);
        sendMessage(count);
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @throws IOException
     * 发送自定义信号，“1”表示告诉前台，数据库发生改变了，需要刷新
     */
    public void sendMessage(Integer count) throws IOException {
        //群发消息
        for(WebSocketServlet item: webSocketSet){
            item.session.getBasicRemote().sendText(count + "");
        }
    }

    /**
     * @ClassName: OnError
     * @Description: 出错的操作
     */
    @OnError
    public void onError(Throwable error){
        System.out.println(error);
        error.printStackTrace();
    }
}