package com.redis.example.demo.threads.interrupt;

/**
 * @author xuleyan
 * @version ThreadSafe.java, v 0.1 2020-12-20 9:55 上午
 */

public class ThreadSafe extends Thread {
    @Override
    public void run() {

        while (!isInterrupted()) {
            System.out.println(isInterrupted());
            //非阻塞过程中通过判断中断标志来退出
            try {
                //阻塞过程捕获中断异常来退出
                System.out.println("开始睡眠：" + System.currentTimeMillis());
                Thread.sleep(5 * 1000);
                System.out.println("结束睡眠：" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
                //捕获到异常之后，执行 break 跳出循环
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafe threadSafe = new ThreadSafe();
        threadSafe.start();
        Thread.sleep(1000);
        System.out.println("睡眠1s结束");
        threadSafe.interrupt();
    }
}
