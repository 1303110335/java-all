/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.rocketmq.provider;

import com.redis.example.demo.rocketmq.MQConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version BatchProducer.java, v 0.1 2020-09-22 2:23 下午
 */
public class BatchProducer {

    public static void main(String[] args) throws MQClientException {
        // 实例化一个生产者来产生延时消息
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        producer.setNamesrvAddr(MQConfig.NAME_SRV_ADDR);
        // 启动生产者
        producer.start();
        String topic = "BatchTest";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
        try {
            SendResult send = producer.send(messages);
            System.out.println("发送消息 : msgId = " + send.getMsgId() + ", 发送状态 = " + send.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}