package com.redis.example.demo.druid.dao.impl;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import com.redis.example.demo.canal.RedisUtil;
import com.redis.example.demo.druid.domain.Account;
import com.redis.example.demo.threads.MyThread;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

//@SpringBootTest
//@RunWith(SpringRunner.class)
@Slf4j
class AccountDAOImplTest {

    @Test
    public void testSave() {
        //new AccountDAOImpl().save(new Account(null, "张三", 25, "18819412345", "广州"));
        new AccountDAOImpl().save(new Account(null, "吴洋", 25, "1412341234", "江西", 0));
    }

    @Test
    public void testMultiThreadSave() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i =0; i < 5; i++) {
            executorService.execute(new MyThread(i));
        }
        try {
            Thread.sleep(4000);
            log.info("sleep 4s...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        new AccountDAOImpl().delete(2L);
    }

    @Test
    public void testUpdate() {
        new AccountDAOImpl().update(new Account(1, "李四", 27, "18819412345", "广州", 0));
    }

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void testUpdateGoods() {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10000; i ++) {
            executorService.execute(() -> {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                getGoodsByLock();
            });
        }

        try {
            countDownLatch.await();
            log.info("主现场{}开始等待" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

    }

    /**
     * 第一种：不加锁
     * 初始值：goodsNum 数据库 100
     * 结果值：goodsNum 数据库0 redis中 -15
     */
    private void getGoods() {
        String key = "user-goods";
        // getGoodsNum
        String goodsNum = RedisUtil.get(key);
        if (goodsNum == null) {
            Account account = new AccountDAOImpl().get(1L);
            RedisUtil.stringSet(key, account.getGoods().toString());
            goodsNum = account.getGoods().toString();
        }
        if (Integer.parseInt(goodsNum) > 0) {
            // 将商品数量减一
            int affectRows = new AccountDAOImpl().updateGoods(1);
            log.info("当前商品数量 : {} ,商品数量减一, affectRows = {}, thread = {}", goodsNum, affectRows, Thread.currentThread().getName());
            // 缓存减一
            RedisUtil.incrBy(key, -1);
        } else {
            countDownLatch.countDown();
            log.info("商品数量为0, 唤醒主线程{}", Thread.currentThread().getName());
        }
    }

    private ThreadLocalRandom random = ThreadLocalRandom.current();
    /**
     * 加锁
     * 2021-07-16 22:16:44.44
     * 2021-07-16 22:16:45.45
     * 2021-07-16 22:16:46.46
     * 2021-07-16 22:16:47.47
     * 2021-07-16 22:16:48.48
     * 2021-07-16 22:16:49.49
     */
    private void getGoodsByLock() {
        String key = "user-goods";
        String lockKey = "lock-key";
        // getGoodsNum
        String goodsNum = RedisUtil.get(key);
        if (goodsNum == null) {
            Account account = new AccountDAOImpl().get(1L);
            RedisUtil.stringSet(key, account.getGoods().toString());
            goodsNum = account.getGoods().toString();
        }

        Integer requestId = random.nextInt(100);
        if (Integer.parseInt(goodsNum) > 0) {
            String s = RedisUtil.setNxPx(lockKey, requestId, 1000);
            if (RedisUtil.LOCK_SUCCESS.equals(s)) {
                log.info("获取锁成功,线程 = {}", Thread.currentThread().getName());
                try {
                    if (Integer.parseInt(RedisUtil.get(key)) > 0) {
                        // 将商品数量减一
                        int affectRows = new AccountDAOImpl().updateGoods(1);
                        log.info("当前商品数量 : {} ,商品数量减一, affectRows = {}, thread = {}", goodsNum, affectRows, Thread.currentThread().getName());
                        //            if (affectRows != 1) {
                        //                // 说明redis中goods数量和mysql中不一致了，mysql 中已经没了，但是redis中还有，需要根据mysql更新redis
                        //
                        //                Account account = new AccountDAOImpl().get(1L);
                        //                RedisUtil.stringSet(key, account.getGoods().toString());
                        //            }
                        // 缓存减一
                        RedisUtil.incrBy(key, -1);
                    } else {
                        countDownLatch.countDown();
                        log.info("商品数量为0, 唤醒主线程{}", Thread.currentThread().getName());
                    }
                } finally {
                    RedisUtil.removeLock(key, requestId.toString());
                    log.info("释放锁,线程 = {}", Thread.currentThread().getName());
                }
            } else {
//                log.info("获取锁失败【前方排队拥挤，请重试】,线程 = {}", Thread.currentThread().getName());
            }
        } else {
            countDownLatch.countDown();
            log.info("商品数量为0, 唤醒主线程{}", Thread.currentThread().getName());
        }
    }

    @Test
    public void testGet() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        for (int i = 0; i < 10; i++) {
//            Account account = new AccountDAOImpl().get(1L);
//            System.out.println(account);
//        }
//        stopWatch.stop();
//        System.out.println("getTotalTimeSeconds = " + stopWatch.getTotalTimeSeconds());


        Account account = new AccountDAOImpl().get(1L);
        System.out.println(account);
        Assert.assertNotNull(account);
    }

    private static final ThreadFactory factory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ThreadPool thread: " + integer.getAndIncrement());
        }
    };


    @Test
    public void testList() throws InterruptedException {

        ExecutorService pool = new ThreadPoolExecutor(10, 20, 500, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(500), factory, new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println("线程池初始化成功...");

        List<Account> list = new AccountDAOImpl().list();
        System.out.println("数据长度为:" + list.size());
        final LinkedList<Account> queue = Lists.newLinkedList(list);
        System.out.println("map开始处理...");


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 灵牌桶
        final RateLimiter rateLimiter = RateLimiter.create(100.0);

//        final Map<String, Integer> resultMap = new HashMap<>();
        final Map<String, Integer> resultMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 20; i++) {
            Runnable task = new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    while (queue.size() >= 1) {
                        Account account = queue.remove(0);
                        if (account != null) {
                            // 获取灵牌
                            double acquire = rateLimiter.acquire();
                            System.out.println("rateLimiter acquire.." + acquire);
                            resultMap.put(account.getName(), account.getId());
                            TimeUnit.MILLISECONDS.sleep(100);
                            System.out.println(account.getName() + "放入map, queue.size =" + queue.size() + ", " + Thread.currentThread().getName());
                        } else {
                            System.out.println("account对象为空");
                        }
                    }
                }
            };
            pool.execute(task);
        }

        while (resultMap.size() != list.size()) {
            System.out.println("主线程yield..., resultMap.size = " + resultMap.size() + ", " + Thread.currentThread().getName());
            Thread.yield();
            TimeUnit.MILLISECONDS.sleep(5000);
        }

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());

        System.out.println(resultMap);
        System.out.println("map处理完毕...");
        pool.shutdown();
    }
}

