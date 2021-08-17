/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.canal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;

@Slf4j
public class CanalClient {
    public static void main(String args[]) {
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress(AddressUtils.getHostIp(), 11111), "example", "", "");
        int batchSize = 1000;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            while (true) {
                // 获取指定数量的数据
                Message message = connector.getWithoutAck(batchSize);
                long batchId = message.getId();
                int size = message.getEntries().size();
                try {
                    if (batchId == -1 || size == 0) {
                        // 未获取binlog 日志信息，睡眠1s
                        Thread.sleep(1000);
                    } else {
                        printEntry(message.getEntries());
                    }
                    connector.ack(batchId); // 提交确认
                } catch (Exception e) {
                    connector.rollback(batchId); // 处理失败, 回滚数据
                }

            }
        } finally {
            connector.disconnect();
        }
    }

    /**
     * @描述 业务操作类
     * @参数
     * @返回值
     * @创建人 zj
     * @创建时间 2020/5/27
     */
    private static void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }
            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    // 数据删除
                    redisDelete(rowData.getBeforeColumnsList(), entry.getHeader().getTableName());
                } else if (eventType == EventType.INSERT) {
                    // 数据添加
                    redisInsert(rowData.getAfterColumnsList(), entry.getHeader().getTableName());
                } else {
                    // 数据修改
                    System.out.println("-------> before");
                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-------> after");
                    redisUpdate(rowData.getAfterColumnsList(), entry.getHeader().getTableName());
                }
            }
        }
    }

    private static void printColumn(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }

    private static void redisInsert(List<Column> columns, String tableName) {
        JSONObject json = new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if (columns.size() > 0) {
            String key = tableName + ":" + columns.get(0).getValue();
            String value = json.toJSONString();
            log.info("add : key = {}, value = {}", key, value);
            RedisUtil.stringSet(key, value);
        }
    }

    private static void redisUpdate(List<Column> columns, String tableName) {
        JSONObject json = new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if (columns.size() > 0) {
            String value = json.toJSONString();
            String key = tableName + ":" + columns.get(0).getValue();
            log.info("update : key = {}, value = {}", key, value);
            RedisUtil.stringSet(key, value);
        }
    }

    private static void redisDelete(List<Column> columns, String tableName) {
        JSONObject json = new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if (columns.size() > 0) {
            String key = tableName + ":" + columns.get(0).getValue();
            log.info("del key = {}", key);
            RedisUtil.delKey(key);
        }
    }
}