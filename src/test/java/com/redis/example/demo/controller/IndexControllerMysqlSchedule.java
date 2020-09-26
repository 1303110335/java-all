package com.redis.example.demo.controller;

import com.xuleyan.frame.extend.redis.jedis.JedisTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class IndexControllerMysqlSchedule {

    @Resource
    private JedisTemplate jedisTemplate;

    private Long start;

    private Long end;



    @Test
    public void index() {
        String set = jedisTemplate.set("qq", "13030110335");
        System.out.println(set);
        String name = jedisTemplate.get("qq");
        System.out.println(name);

        jedisTemplate.lpush("list", "java", "php", "c");
        String java = jedisTemplate.lpop("list");
        System.out.println(java);
        String php = jedisTemplate.lpop("list");
        System.out.println(php);
        String c = jedisTemplate.lpop("list");
        System.out.println(c);

        jedisTemplate.hset("database", "table", "haha");
        String hget = jedisTemplate.hget("database", "table");
        System.out.println(hget);
    }

    @Test
    public void testRedis() {
        List<String> nameList = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
        int len = nameList.size();
        for (int i = 0; i < 10000; i++) {
            int index = i % len;
            jedisTemplate.setNx(i + "", nameList.get(index));
        }
    }

    @BeforeEach
    public void start() {
        start = System.currentTimeMillis();
        System.out.println("@Before");
    }

    /**
     * 无pipeline
     * 10000 耗时：843ms
     * 100000 耗时：8085ms
     */
    @Test
    public void testNoPipeline() {
        //连接redis服务器，192.168.0.100:6379
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        List<String> nameList = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
        int len = nameList.size();
        for (int i = 0; i < 100000; i++) {
            int index = i % len;
            jedis.set(i + "", nameList.get(index));
        }
        jedis.close();
        System.out.println("testNoPipeline结束");
    }

    /**
     * 有pipeline
     * 10000 耗时：74ms
     * 100000 耗时：405ms
     */
    @Test
    public void testPipeline() {

        //连接redis服务器，192.168.0.100:6379
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        Pipeline pipeline = jedis.pipelined();
        List<String> nameList = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
        int len = nameList.size();
        for (int i = 0; i < 100000; i++) {
            int index = i % len;
            pipeline.mset(i + "", nameList.get(index));
        }
        // 同步
        pipeline.sync();
        jedis.close();
        System.out.println("testPipeline结束");
    }

    @AfterEach
    public void end() {
        System.out.println("@After");
        end = System.currentTimeMillis();
        System.out.println( "耗时:" + (end - start) + "ms");
    }


}