/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.service;

import com.redis.example.demo.utils.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author xuleyan
 * @version JdbcService.java, v 0.1 2020-09-15 3:01 下午
 */
//@Service
@Slf4j
public class JdbcServicePool {

    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    @Value("${druid.yuhang.username}")
    private String username;

    @Value("${druid.yuhang.password}")
    private String password;

    @Value("${druid.yuhang.url}")
    private String dbUrl;

    /**
     * 将数据库连接放入到一个阻塞队列中进行获取
     */
    private BlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(4);

    @PostConstruct
    public void init() {
        Connection conn = null;

        try {
            for (int i = 0; i < 4; i++) {
                // 加载数据库驱动类
                Class.forName(DRIVER_CLASS);
                // 获取数据库连接对象
                conn = DriverManager.getConnection(dbUrl, username, password);
                connections.add(conn);
                log.info("加入conn数据库连接到连接池connections >> conn = {}, poolSize = {}", conn, connections.size());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public <T> ResponseWrapper<T> query(String sql, String fieldName) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        // 数据库连接对象
        // CallableStatement对象
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<String> tableNameList = new ArrayList<>();
            while (resultSet.next()) {
                String tableName = resultSet.getString(fieldName);
                tableNameList.add(tableName);
            }
            System.out.println("tableNameList:" + tableNameList );
            responseWrapper.setData((T) tableNameList);
            return responseWrapper;
        } catch (Exception ex) {
            log.error("查询数据错误", ex);
        } finally {
            closeOperate(preparedStatement, resultSet, conn);
        }
        return null;
    }

    /**
     * 执行存储过程
     * @param now 当前时间
     * @param proc 存储过程名称
     * @param time 向前推移的时间 minutes
     * @return
     */
    public ResponseWrapper<String> execProcedure(Date now, String proc, Integer time) {
        ResponseWrapper<String> responseWrapper = new ResponseWrapper<>();
        // 数据库连接对象
        Connection conn = null;
        // CallableStatement对象
        CallableStatement cstmt = null;
        try {
            // 获取数据库连接
            conn = getConnection();
            cstmt = conn.prepareCall("{call "  + proc +"(?,?,?)}");
            // @flag
            cstmt.registerOutParameter(3, Types.INTEGER);
            // 15
            cstmt.setInt(1, time);
            // 2020-08-27 15:12:00
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(now);
            cstmt.setString(2, format);
            Thread.sleep(1000);
            log.info("execProcedure >> sleep 10s");
            //执行
            cstmt.execute();
            //把第3个参数的值当成int类型拿出来, flag 为1则表示执行成功
            int flag = cstmt.getInt(3);
            log.info("execProcedure >> 执行存储过程成功 >> proc = {}, flag = {}, now = {}, time = {}", proc, flag, format, time);
            if (flag != 1) {
                responseWrapper.setSuccess(false);
            }
            return responseWrapper;
        } catch (SQLException | InterruptedException ex) {
            log.error("execProcedure >> 执行存储过程失败 >> proc = {}", proc, ex);
            responseWrapper.setSuccess(false);
            responseWrapper.setErrMsg(ex.getMessage());
        } finally {
            // 关闭数据库操作对象
            closeOperate(cstmt, null, conn);
        }
        return responseWrapper;
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接对象
     * @author xly
     */
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = (Connection) ((ArrayBlockingQueue) connections).take();
            log.info("直接获取连接 >> conn = {}", conn);
        } catch (Exception ex) {
            log.error("getConnection >> 数据库连接异常 ", ex);
        }
        return conn;
    }

    /**
     * 关闭数据库操作对象
     *
     * @param stmt Statement对象
     * @param resultSet resultSet对象
     * @author xly
     */
    private void closeOperate(Statement stmt, ResultSet resultSet, Connection conn) {
        try {
            // 关闭Statement对象
            if (stmt != null) {
                stmt.close();
            }
            // 关闭Connection对象
            if (resultSet != null) {
                resultSet.close();
            }
            log.info("closeConnection >> 关闭连接 >> conn = {}", conn);
            if (conn != null) {
                ((ArrayBlockingQueue) connections).offer(conn);
                //conn.close();
            }
            log.info("关闭数据库操作对象完成");
        } catch (SQLException ex) {
            log.error("closeOperate >> 关闭数据库操作对象异常", ex);
        }
    }

}