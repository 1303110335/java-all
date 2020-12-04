package com.redis.example.demo.service;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.redis.example.demo.threads.ProcThread;
import com.redis.example.demo.utils.DateTimeUtils;
import com.redis.example.demo.utils.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@RunWith(SpringRunner.class)
@Slf4j
class JdbcServiceTest {

    @Resource
    private JdbcServicePool jdbcService;


    /**
     *
     * 一般我们创建的多线程都是非守护线程.
     * 但是也有例外,例如在junit环境中 创建的多线程都变成了守护线程模式.
     * 在 main 中创建的多线程是非守护线程模式,所以只要子线程未执行结束, main线程会处于等待状态 ,这是程序进程也不会结束.
     *
     * 使用同一个连接 1.413322192s 1.651044038s 1.854567828s
     * 不使用同一个连接 6.786933157s
     */
    @Test
    void execute() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ExecutorService executorService = new ThreadPoolExecutor(
                10, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), r -> {
                    Thread thread = new Thread(r);
                    thread.setName("testMysqlThread");
                    return thread;
                });

        for (int i = 0; i < 10; i ++) {
            executorService.execute(new ProcThread(i, jdbcService));
        }

        stopWatch.stop();
        log.info("任务共计耗时{}s", stopWatch.getTotalTimeSeconds());

        try {
            Thread.sleep(10000);
            log.info("sleep 40s...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void query() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int overTime = 2;
        String sql = "select table_name from stg_table_name";
        ResponseWrapper<Object> responseWrapper = jdbcService.query(sql, "table_name");
        List<String> tableNameList = (List<String>) responseWrapper.getData();
        log.info("scanTableList >> 查询stg_table_name获取扫描的表的列表 >> tableNameList = {}", tableNameList);

        if (!CollectionUtils.isEmpty(tableNameList)) {
            for (String tableName : tableNameList) {
                String timeSql = "select max(loadertime) as loadertime from %s";
                responseWrapper = jdbcService.query(String.format(timeSql, tableName), "loadertime");
                List<String> loaderTimeList = (List<String>) responseWrapper.getData();
                String loaderTime = loaderTimeList.get(0);
                // 将loadertime与当前时间进行比较，若超过2分钟(可配置)则报警 xxx表未更新超过2分钟
                Date loaderDate = DateTimeUtils.parse(loaderTime, DateTimeUtils.NORMAL_DATETIME_PATTERN);
                Date now = new Date();
                if ((now.getTime() - loaderDate.getTime()) / 60000 > overTime) {
                    log.warn("表名：{}, 超过{}分钟未更新", tableName, overTime);
                } else {
                    log.info("表名：{}更新时间正常", tableName);
                }
            }
        }
        stopWatch.stop();
        log.info("任务共计耗时{}s", stopWatch.getTotalTimeSeconds());
    }
}