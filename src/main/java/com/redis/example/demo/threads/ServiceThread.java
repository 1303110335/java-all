/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.threads;

import org.apache.rocketmq.common.CountDownLatch2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author xuleyan
 * @version ServiceThread.java, v 0.1 2020-11-22 3:52 下午
 */
public abstract class ServiceThread implements Runnable {

    protected final CountDownLatch2 waitPoint = new CountDownLatch2(1);

    //刷盘资源是否被占用的标识
    protected volatile AtomicBoolean hasNotified = new AtomicBoolean(false);

    protected void waitForRunning(long interval) {
        if (hasNotified.compareAndSet(true, false)) {
            System.out.println("比较后直接返回");
            this.onWaitEnd();
            return;
        }

        // 重置count
        waitPoint.reset();

        try {
            System.out.println("等待"+ interval +"ms");
            waitPoint.await(interval, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //在退出此方法前设置hasNotified==false，也就是说下次循环时会触发休眠
            hasNotified.set(false);
            this.onWaitEnd();
        }

    }

    /**
     * 唤醒,立即终止{@link #waitPoint}的await(),同时更新hasNotified==true,表示之前在休眠状态,立即中断{@link #waitForRunning(long)}
     */
    //@SuppressWarnings("SpellCheckingInspection")
    public void wakeup() {
        System.out.println("唤醒刷盘");
        if (hasNotified.compareAndSet(false, true)) {
            waitPoint.countDown(); // notify
        }
    }

    protected void onWaitEnd() {
    }

}