/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.rocketmq.provider;

import com.redis.example.demo.rocketmq.MQConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

/**
 * 事务性生产者
 * @author xuleyan
 * @version TransactionMQProducer.java, v 0.1 2020-09-22 2:48 下午
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionListener transactionListener = new TransactionListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("group-trans");
        ExecutorService executorService = new ThreadPoolExecutor(
                2, 5, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-transaction-msg-check-thread");
                        return thread;
                    }
                });

        producer.setNamesrvAddr(MQConfig.NAME_SRV_ADDR);
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TopicTest1234", tags[i % tags.length], "KEY" + i,
                        ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", transactionSendResult);
                Thread.sleep(10);
            } catch (UnsupportedEncodingException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 100000; i ++) {
            Thread.sleep(1000);
        }
        producer.shutdown();
    }
}