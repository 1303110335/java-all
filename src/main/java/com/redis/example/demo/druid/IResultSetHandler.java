/**
 * bianque.com
 * Copyright (C) 2013-2020All Rights Reserved.
 */
package com.redis.example.demo.druid;

import java.sql.ResultSet;

/**
 *
 * @author xuleyan
 * @version IResultSetHandler.java, v 0.1 2020-09-24 10:08 下午
 */
public interface IResultSetHandler<T> {

    /**
     * 结果集操作模板
     * @param rSet
     * @return
     */
    T handler(ResultSet rSet);
}