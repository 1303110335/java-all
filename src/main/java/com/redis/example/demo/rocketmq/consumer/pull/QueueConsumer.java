/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.rocketmq.consumer.pull;

import com.redis.example.demo.rocketmq.MQConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.PullResultExt;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author xuleyan
 * @version QueueConsumer.java, v 0.1 2020-09-25 4:12 下午
 */
public class QueueConsumer {

    private static final Map<MessageQueue, Long> offsetTable = new HashMap<MessageQueue, Long>();

    public static void main(String[] args) throws Exception {
        offsetTable.clear();
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("pullConsumer");
        consumer.setNamesrvAddr(MQConfig.NAME_SRV_ADDR);
        consumer.start();
        try {
            Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("TopicTest");
            for (MessageQueue mq : mqs) {
                System.out.println("当前获取的消息的归属队列是: " + mq.getQueueId());

                PullResultExt pullResult = (PullResultExt) consumer.pullBlockIfNotFound(
                        mq, null, getMessageQueueOffset(mq), 100);
                putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                switch (pullResult.getPullStatus()) {

                    case FOUND:

                        List<MessageExt> messageExtList = pullResult.getMsgFoundList();
                        for (MessageExt m : messageExtList) {
                            System.out.println("收到了消息:" + new String(m.getBody()));
                        }
                        break;
                    case NO_MATCHED_MSG:
                        break;
                    case NO_NEW_MSG:
                        break;
                    case OFFSET_ILLEGAL:
                        break;
                    default:
                        break;
                }
            }

        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offsetTable.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offsetTable.get(mq);
        if (offset != null) {
            return offset;
        }
        return 0;
    }

}