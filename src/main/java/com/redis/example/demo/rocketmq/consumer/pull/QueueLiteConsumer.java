/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.rocketmq.consumer.pull;

import com.redis.example.demo.rocketmq.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 *
 * @author xuleyan
 * @version QueueConsumer.java, v 0.1 2020-09-23 11:31 下午
 */
@Slf4j
public class QueueLiteConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("pullConsumer");
        consumer.setNamesrvAddr(MQConfig.NAME_SRV_ADDR);
        consumer.subscribe("TopicTest", "*");
        consumer.start();
        try {
            // 循环开始消费消息
            while (true) {
                List<MessageExt> messageExts = consumer.poll();
                for (MessageExt messageExt : messageExts) {
                    int queueId = messageExt.getQueueId();
                    String msgId = messageExt.getMsgId();
                    long queueOffset = messageExt.getQueueOffset();
                    String body = new String(messageExt.getBody(), StandardCharsets.UTF_8);
                    log.info("queueId = {}, msgId = {}, queueOffset = {}, body = {}", queueId, msgId, queueOffset, body);
                }
                //System.out.printf("%s%n", messageExts);
            }
        } finally {
            consumer.shutdown();
        }

    }
}