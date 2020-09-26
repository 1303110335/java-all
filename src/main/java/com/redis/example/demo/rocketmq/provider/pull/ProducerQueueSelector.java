/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.rocketmq.provider.pull;

import com.redis.example.demo.rocketmq.MQConfig;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author xuleyan
 * @version ProducerQueueSelector.java, v 0.1 2020-09-25 4:37 下午
 */
public class ProducerQueueSelector {

    public static void main(String[] args) throws Exception {

        // 声明并初始化一个producer
        DefaultMQProducer producer = new DefaultMQProducer("producer1");

        // 设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
        // NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
        producer.setNamesrvAddr(MQConfig.NAME_SRV_ADDR);
        producer.setVipChannelEnabled(false);//3.2.6的版本没有该设置，在更新或者最新的版本中务必将其设置为false，否则会有问题
        producer.setRetryTimesWhenSendFailed(3);

        // 调用start()方法启动一个producer实例
        producer.start();

        // 发送10条消息到Topic为TopicTest，tag为TagA，消息内容为“Hello RocketMQ”拼接上i的值
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TopicTest",
                        "TagA",
                        "i" + i, ("Hello RocketMQ " + i).getBytes(StandardCharsets.UTF_8)
                );

                // 调用producer的send()方法发送消息
                // 这里调用的是同步的方式，所以会有返回结果
                SendResult sendResult = producer.send(msg);

                //指定消息投递的队列，同步的方式，会有返回结果
				/*SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
					@Override
					public MessageQueue select(List<MessageQueue> queues, Message msg, Object queNum) {
						int queueNum = Integer.parseInt(queNum.toString());
						return queues.get(queueNum);
					}
				}, 0);*/

                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "," + i);
                // System.out.println(sendResult.getSendStatus()); //发送结果状态
                // 打印返回结果，可以看到消息发送的状态以及一些相关信息
                System.out.println("当前消息投递到的队列是 : " + sendResult.getMessageQueue().getQueueId());
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        // 发送完消息之后，调用shutdown()方法关闭producer
        producer.shutdown();

    }

}