/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.rocketmq.consumer.push;

import com.redis.example.demo.rocketmq.MQConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

import static org.apache.rocketmq.common.message.MessageConst.PROPERTY_MAX_OFFSET;

/**
 *
 * @author xuleyan
 * @version SkipSomeMessage.java, v 0.1 2020-09-22 5:41 下午
 */
public class SkipSomeMessage {

    public static void main(String[] args) throws MQClientException {
        // 创建消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group-java");

        // 设置broker地址
        consumer.setNamesrvAddr(MQConfig.NAME_SRV_ADDR);
        consumer.subscribe("TopicTest", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                long offset = msgs.get(0).getQueueOffset();
                String maxOffset = msgs.get(0).getProperty(PROPERTY_MAX_OFFSET);
                long diff = Long.parseLong(maxOffset) - offset;
                if (diff > 800) {
                    // TODO 消息堆积情况的特殊处理
                    System.out.println("消息堆积处理：diff = " + diff);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                // TODO 正常消费过程
                System.out.println("正常消费：diff = " + diff);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("Consumer Started.");
    }
}