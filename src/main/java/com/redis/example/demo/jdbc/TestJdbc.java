/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.jdbc;


import cn.hutool.db.ds.simple.SimpleDataSource;
//import com.aliyun.odps.jdbc.OdpsDriver;
import com.aliyun.odps.Instance;
import com.aliyun.odps.Odps;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.account.AliyunAccount;
import com.aliyun.odps.task.SQLTask;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author xuleyan
 * @version TestJdbc.java, v 0.1 2021-04-23 9:34 上午
 */
public class TestJdbc {

    private static final String accessId = "fSxHRWzWxIlUCNuK";
    private static final String accessKey = "ONtak20F0rvLBdQu3CokfzBiYyEC2p";

//    public static void main(String[] args) throws SQLException {
//        try {
//            Class.forName("com.aliyun.odps.jdbc.OdpsDriver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//        // fill in the information here
//        String accessId = "fSxHRWzWxIlUCNuK";
//        String accessKey = "ONtak20F0rvLBdQu3CokfzBiYyEC2p";
//        Connection conn = DriverManager.getConnection("jdbc:odps:http://service.cn-hangzhou-zjwjw-d01.odps.ops.bianque-app.com:80/api?project=bqdata_hangzhou_prod", accessId, accessKey);
//        Statement stmt = conn.createStatement();
//        // select * query
////        String sql = "select HDSD00_01_020 from connect_yhqy_patient_allergen where HDSD00_01_001='03301020010611058';";
//        String sql = "select count(*) from connect_yhqy_patient_allergen";
//        System.out.println("Running: " + sql);
//        ResultSet rs = stmt.executeQuery(sql);
//        while (rs.next()) {
//            System.out.println(String.valueOf(rs.getInt(1)) + "\t" + rs.getString(2));
//        }
//
//        // do not forget to close
//        stmt.close();
//        conn.close();
//    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUrl();
//        dataSource.setPassword();
//
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


//        OdpsDriver driver = new OdpsDriver();
//        String url = "";
//        Properties conProps = new Properties();
//        DataSource dataSourceOdps = new SimpleDriverDataSource(driver, url, conProps);


//        Properties config = new Properties();
//        config.put("access_id", "fSxHRWzWxIlUCNuK");
//        config.put("access_key", "ONtak20F0rvLBdQu3CokfzBiYyEC2p");
//        config.put("project_name", "bqdata_hangzhou_prod");
//        String url = "http://service.cn-hangzhou-zjwjw-d01.odps.ops.bianque-app.com:80/api";
//        String className = "com.aliyun.odps.jdbc.OdpsDriver";
//        String sql = "select HDSD00_01_020 from connect_yhqy_patient_allergen where HDSD00_01_001='03301020010611058';";
//        testQuery(className, config, url, sql);

//        Properties prop = new Properties();
//        prop.put("user", "root");
//        prop.put("password", "a12345678");
//        String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/test";
//        String mysqlClassName = "com.mysql.jdbc.Driver";
//        String mysql = "select * from user";
//        testQuery(mysqlClassName, prop, mysqlUrl, mysql);


        Properties oracleProp = new Properties();
        oracleProp.put("user", "sxzzjk");
        oracleProp.put("password", "sxzzjk");
        String oracleUrl = "jdbc:oracle:thin:@47.99.177.166:1538:orcl";
        String oracleClassName = "oracle.jdbc.OracleDriver";
        String oracleSql = "SELECT\n" +
                "  t.转往机构编号 as targetOrgCode,\n" +
                "  t.转往机构名称 as targetOrgName,\n" +
                "  t.xingming AS name,\n" +
                "  t.SHENFENZH AS idCard,\n" +
                "  t.FUWUJGMC AS oriOrgName,\n" +
                "  t.FUWUJGBH AS oriOrgCode,\n" +
                "  t.zhuanzhenrq AS referralDate,\n" +
                "  t.tijiaorq AS createTime,\n" +
                "  t.SHENHEBZ as auditStatus,\n" +
                "  t.zhuanzhenrq\n" +
                "FROM\n" +
                "  vbq_zz_xiazhuan t\n" +
                "WHERE\n" +
                "  zhuanzhenrq >= sysdate -2 order by zhuanzhenrq";
        testQuery(oracleClassName, oracleProp, oracleUrl, oracleSql);


//        OdpsConf odpsConf = new OdpsConf();
//        odpsConf.setAccessId(accessId);
//        odpsConf.setAccessKey(accessKey);
//        odpsConf.setProject("bqdata_hangzhou_prod");
//        odpsConf.setUrl("http://service.cn-hangzhou-zjwjw-d01.odps.ops.bianque-app.com:80/api");
//        testOdpsQuery(odpsConf);
    }

    public static Odps createTaskSqlOdps(OdpsConf odpsConf) {
        Odps odps = new Odps(new AliyunAccount(odpsConf.getAccessId(), odpsConf.getAccessKey()));
        odps.setEndpoint(odpsConf.getUrl());
        odps.setDefaultProject(odpsConf.getProject());
        return odps;
    }

    private static void testOdpsQuery(OdpsConf odpsConf) {
        String sql = "select HDSD00_01_020 from connect_yhqy_patient_allergen where HDSD00_01_001='03301020010611058';";
        Odps odps = createTaskSqlOdps(odpsConf);
        try {
            Instance instance = SQLTask.run(odps, sql);
            instance.waitForSuccess();
            Map<String, String> taskResults = instance.getTaskResults();
            String anonymousSQLTask = taskResults.get("AnonymousSQLTask");
            System.out.println(anonymousSQLTask);
        } catch (OdpsException e) {
            e.printStackTrace();
        }
    }

    private static void testQuery(String className, Properties config, String url, String sql) {

        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, config);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("business_key");
                System.out.println(" business_key = " + name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        close(conn, stmt, rs);

    }

    private static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}