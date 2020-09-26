/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.druid;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version BeanHandler.java, v 0.1 2020-09-24 10:17 下午
 */
@Slf4j
public class BeanHandler<T> implements IResultSetHandler<List<T>> {

    /**
     * 创建字节码对象
     */
    private Class<T> clazz;

    /**
     * 创建有参构造函数，用于传入具体操作对象的类型
     * @param clazz
     */
    public BeanHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 数据库操作类
     * @param rSet
     * @return
     */
    @Override
    public List<T> handler(ResultSet rSet) {
        // 创建 List 用于存放装箱后的对象
        List<T> list = new ArrayList<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            // 对结果集进行装箱操作
            while (rSet.next()) {
                T obj = clazz.newInstance();
                for (PropertyDescriptor descriptor : descriptors) {
                    Object value = rSet.getObject(descriptor.getName());
                    //log.info("name = {}, type = {}", descriptor.getName(), value.getClass());
                    descriptor.getWriteMethod().invoke(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}