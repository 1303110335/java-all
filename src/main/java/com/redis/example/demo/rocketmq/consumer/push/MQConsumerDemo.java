/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.rocketmq.consumer.push;

import com.redis.example.demo.rocketmq.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version RocketMQConsumerDemo.java, v 0.1 2020-08-10 8:36 下午
 */
@Slf4j
public class MQConsumerDemo {

    public static void main(String[] args) throws MQClientException {

        // 创建消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group-java");

        // 设置broker地址
        consumer.setNamesrvAddr(MQConfig.NAME_SRV_ADDR);
//        consumer.subscribe("topicB", "*");
        consumer.subscribe("TopicTest", "*");
//        consumer.subscribe("BatchTest", "*");
//        consumer.subscribe("TopicTest1234", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                for (MessageExt messageExt : list) {
                    System.out.println("接受消息：队列ID==>"+messageExt.getQueueId() + " 消息Id:" + messageExt.getMsgId()
                            + " 内容:" + new String(messageExt.getBody(), StandardCharsets.UTF_8));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

    }
}