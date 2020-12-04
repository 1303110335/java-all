package com.redis.example.demo.druid.dao.impl;

import com.redis.example.demo.druid.domain.Account;
import com.redis.example.demo.threads.MyThread;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@SpringBootTest
//@RunWith(SpringRunner.class)
@Slf4j
class AccountDAOImplTest {

    @Test
    public void testSave() {
        //new AccountDAOImpl().save(new Account(null, "张三", 25, "18819412345", "广州"));
        new AccountDAOImpl().save(new Account(null, "吴洋", 25, "1412341234", "江西"));
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
        new AccountDAOImpl().update(new Account(1, "李四", 27, "18819412345", "广州"));
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
        Assert.assertNotNull(account);
    }

    @Test
    public void testList() {
        System.out.println(new AccountDAOImpl().list());
    }
}