/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.druid.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author xuleyan
 * @version DruidUtil.java, v 0.1 2020-09-24 10:03 下午
 */
@Slf4j
public class DruidUtil {

    /**
     * 工具类，私有化无参构造函数
     */
    private DruidUtil() {
    }

    /**
     * 静态数据源变量，供全局操作且用于静态代码块加载资源。
     */
    private static DataSource dataSource;

    /**
     * 加载配置文件
     */
    static {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            log.info("static >> 加载配置文件");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            log.info("connection = {}", connection);
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        throw new RuntimeException("数据库连接异常");
    }

    /**
     * 释放数据库连接 connection 到数据库缓存池，并关闭 rSet 和 pStatement 资源
     * @param rSet 数据库处理结果集
     * @param pStatement 数据库操作语句
     * @param connection 数据库连接对象
     */
    public static void releaseSqlConnection(ResultSet rSet, PreparedStatement pStatement, Connection connection) {
        log.info("releaseSqlConnection >> connection = {}", connection);
        try {
            if (rSet != null) {
                rSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}