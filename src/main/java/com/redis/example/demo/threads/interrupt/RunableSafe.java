package com.redis.example.demo.threads.interrupt;

/**
 * @author xuleyan
 * @version ThreadSafe.java, v 0.1 2020-12-20 9:55 上午
 */

public class RunableSafe implements Runnable {
    @Override
    public void run() {

        while (true) {
            //非阻塞过程中通过判断中断标志来退出
            try {
                // 检测到中断之后，需要自己return，不然程序还会继续进行
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
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
        RunableSafe threadSafe = new RunableSafe();
        Thread thread = new Thread(threadSafe);
        thread.start();
        Thread.sleep(1000);
        System.out.println("睡眠1s结束");
        thread.interrupt();
    }
}
