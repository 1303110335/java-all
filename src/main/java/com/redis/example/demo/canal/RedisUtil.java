/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.canal;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 *
 * @author xuleyan
 * @version RedisUtil.java, v 0.1 2021-07-16 4:06 下午
 */
public class RedisUtil {

    // Redis服务器IP
    private static String ADDR = "127.0.0.1";

    // Redis的端口号
    private static int PORT = 6379;

    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    // 过期时间
    protected static int  expireTime = 60 * 60 * 24;

    // 连接池
    protected static JedisPool pool;

    public static final String LOCK_SUCCESS = "OK";

    public static final String DEL_SUCCESS = "1";

    /**
     * 静态代码，只在初次调用一次
     */
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(MAX_ACTIVE);
        //最多空闲实例
        config.setMaxIdle(MAX_IDLE);
        //超时时间
        config.setMaxWaitMillis(MAX_WAIT);
        //
        config.setTestOnBorrow(false);

        pool = new JedisPool(config, ADDR, PORT, 1000, "123456");
    }

    /**
     * 获取jedis实例
     */
    protected static synchronized Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            if (jedis != null) {
                jedis.close();
            }
        }
        return jedis;
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    protected static void closeResource(Jedis jedis) {
        try {
            jedis.close();
        } catch (Exception e) {

        }
    }


    /**
     * 删除key
     * @param key
     */
    public static void delKey(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(jedis);
        }
    }

    public static String get(String key) {
        Jedis jedis = null;
        String val = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            val = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(jedis);
        }
        return val;
    }

    public static void incrBy(String key, Integer num) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            jedis.incrBy(key, num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(jedis);
        }
    }

    /**
     * 设置成功返回 OK, 失败返回 null
     * @param key
     * @param id
     * @param pxTime
     * @return
     */
    public static String setNxPx(String key, Integer id, Integer pxTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            SetParams setParams = new SetParams();
            setParams.nx();
            setParams.px(pxTime);
            return jedis.set(key, id.toString(), setParams);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(jedis);
        }
        return null;
    }

    public static void main(String[] args) {
//        // OK
//        String res = RedisUtil.setNxPx("hahaKey", 10, 1000);
//        // null
//        String res2 = RedisUtil.setNxPx("hahaKey", 10, 1000);
//        System.out.println(res);
//        System.out.println(res2);

        RedisUtil.removeLock("name", "haha");
    }

    /**
     * 添加string数据
     * @param key
     * @param value
     */
    public static String stringSet(String key, String value) {
        Jedis jedis = null;
        String lastVal = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            lastVal = jedis.set(key, value);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(jedis);
        }
        return lastVal;
    }

    /**
     * 当requestId相同时删除key
     * @param lockKey
     * @param requestId
     */
    public static boolean removeLock(String lockKey, String requestId) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            // 1成功 0失败
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

            if (DEL_SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}