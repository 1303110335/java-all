/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version ZkLock.java, v 0.1 2021-07-15 3:35 下午
 */
public class ZkLock {

    static CuratorFramework framework= null;
    static {
        framework = CuratorFrameworkFactory.newClient(
                "127.0.0.1:2181",
                20000,
                20000,
                new RetryNTimes(3, 5000));
        framework.start();
    }

    /**
     * 获取互斥锁
     * @param name
     * @return
     * @throws Exception
     */
    public InterProcessMutex getLock(String name) throws Exception {
        return  new InterProcessMutex(framework,buildPath(name));
    }

    /**
     * 获取互斥锁
     * @param name
     * @return
     * @throws Exception
     */
    public InterProcessMutex getMutexLock(String name) throws Exception {
        return  new InterProcessMutex(framework,buildPath(name));
    }

    /**
     * 获取读写锁
     * @param name
     * @return
     * @throws Exception
     */
    public InterProcessReadWriteLock getReadWriteLock(String name) throws Exception {
        return new InterProcessReadWriteLock(framework,buildPath(name));
    }

    /**
     * 获取不可重入互斥锁
     * @param name
     * @return
     * @throws Exception
     */
    public InterProcessSemaphoreMutex getSemaphoreLock(String name) throws Exception {
        return new InterProcessSemaphoreMutex(framework,buildPath(name));
    }

    /**
     * 获取多锁  集合锁
     * @param names
     * @return
     * @throws Exception
     */
    public InterProcessMultiLock getMutilLock(List<String> names) throws Exception {
        return  new InterProcessMultiLock(framework,mutilPath(names));
    }

    public List<String> mutilPath(List<String> names){
        List<String> paths = new ArrayList<>();
        for(String name :names)
        {
            paths.add(buildPath(name));
        }
        return paths;
    }


    public String buildPath(String name)
    {
        StringBuilder path = new StringBuilder();
        String[] roots = new String[]{"mg","mylock"};
        for(String str : roots)
        {
            if(str.startsWith("/")){
                path.append("/");
            }
            path.append("/").append(str);
        }
        path.append("/").append(name);
        return path.toString();
    }
}