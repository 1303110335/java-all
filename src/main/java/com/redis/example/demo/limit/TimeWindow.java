/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.limit;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;
/**
 *
 * @author xuleyan
 * @version TimeWindow.java, v 0.1 2020-11-17 3:13 下午
 */
public class TimeWindow {

    private ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();

    /**
     * 间隔秒数
     */
    private int seconds;

    /**
     * 最大限流
     */
    private int max;

    public TimeWindow(int max, int seconds) {
        this.seconds = seconds;
        this.max = max;

        new Thread(() -> {
           while (true) {
               try {
                   Thread.sleep((seconds - 1) * 1000L);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               clean();
           }
        }).start();
    }

    public static void main(String[] args) {
        final TimeWindow timeWindow = new TimeWindow(5, 1);

        IntStream.range(0, 3).forEach((i) -> {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(new Random().nextInt(20) * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeWindow.take();
                }
            }).start();
        });
    }

    /**
     * 获取令牌，并且添加时间
     */
    private void take() {
        try {
            int size = sizeOfValid();
            if (size > max) {
                System.err.println("超限");
            }
            synchronized (queue) {
                if (sizeOfValid() > max) {
                    System.err.println("超限");
                    System.err.println("[queue中有] " + queue.size() + ", 最大数量:" + max);
                    return;
                }
                this.queue.offer(System.currentTimeMillis());
            }
            System.out.println("queue中数据的数量为 " + queue.size() + ", 最大数量:" + max);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int sizeOfValid() {
        Iterator<Long> it = queue.iterator();
        Long ms = System.currentTimeMillis() - seconds * 1000;
        int count = 0;
        while (it.hasNext()) {
            Long t = it.next();
            if (t > ms) {
                count ++;
            }
        }
        return count;
    }

    /**
     * 清理过期时间
     */
    public void clean() {
        Long c = System.currentTimeMillis() - seconds * 1000;
        Long tl = null;
        while ((tl = queue.peek()) != null && tl < c) {
            System.out.println("清理数据");
            queue.poll();
        }
    }
}