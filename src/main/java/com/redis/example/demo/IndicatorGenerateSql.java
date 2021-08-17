/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo;

import com.alibaba.excel.EasyExcel;
import com.redis.example.demo.druid.BeanHandler;
import com.redis.example.demo.druid.JdbcTemplate;
import com.redis.example.demo.druid.domain.IndicatorDTO;
import com.redis.example.demo.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 指标数据生成，指标关系生成，指标excel文件生成
 * 造数据
 * UPDATE hospital_indicator_month_result  SET indicator_num = ROUND( RAND() * 100 ) WHERE indicator_code IN ( SELECT indicator_code FROM hospital_indicator_info WHERE template_code = '8' AND `level` = 2);
 * @author xuleyan
 * @version GenerateSql.java, v 0.1 2020-12-01 3:17 下午
 */
@Slf4j
public class IndicatorGenerateSql {


    private static final String insertStr = "INSERT INTO `hospital_indicator_info`(`org_code`,           `hospital_code`,      `template_code`, `level`, `tag`, `item_code`, `parent_code`, `indicator_code`, `indicator_name`, `indicator_unit`, `indicator_des`, `formula`, `num_type`, `item_type`, `sort`, `creator`, `gmt_create`, `modifier`, `gmt_modified`, `is_deleted`, `cycle`, `filled`, `filled_time`) VALUES \n";
    private static final String str = "('91330110MA2B1M4K5X', '91330110MA2B1M4K5X', '%s',            '%d',    '%d',  '%s',        '%s',           '%s',             '%s',            '%s',               NULL,           NULL,      '1',       '1',          %d,    'DBA',     NULL,        NULL,       '%s',           0,             '%s',    2,        NULL), \n";

    private static final String relationInsertStr = "INSERT INTO `hospital_role_indicator_relation`(`hospital_code`, `role_code`, `indicator_code`, `creator`, `gmt_create`, `modifier`, `gmt_modified`, `is_deleted`) VALUES \n";
    private static final String relationStr = "('91330110MA2B1M4K5X', '20200222195514428683239939522560', '%s', '20200429102748452820426960285696', '%s', '20200429102748452820426960285696', '%s', 0), \n";
    private static final String datetime = DateFormatUtils.format(new Date(), DateTimeUtils.NORMAL_DATETIME_PATTERN);

    private static final String path = "target/threads/";
    /**
     * 时间类型：month,year,quarter
     */
    private static String indicatorUnit = "个";
    /**
     * 模板类型：1:普通类型2:舒心就医,3:健康运行,4:财务大屏,5:流感监测,6:合理用药,7:最多跑一次,8:卫生资源,9:健康管理,10:医疗质量,11:医改监测
     */
    private static String templateCode = "1";

    private StringBuilder resultIndicatorBuilder = new StringBuilder();
    private StringBuilder resultRelationBuilder = new StringBuilder();

    public static void main(String[] args) {
        IndicatorGenerateSql generateSql = new IndicatorGenerateSql();

        String title = "冠疫苗数";
        String type = "year";
        String[] sonTitleArr = {"今日新冠疫苗数", "累计新冠疫苗数"};
        generateSql.runSingle(sonTitleArr, title, type);

//        String title2 = "药品管理";
//        String[] sonTitleArr2 = {"药品差价","药品差价率","中药饮片差价","中药饮片差价率"};
//        String type2 = "year";
//        String[] hospitalList = {"区一院", "区二院", "区三院", "区中院", "区五院", "区妇保", "区良渚"};
//        generateSql.runMany(sonTitleArr2, hospitalList, title2, type2);

        String templateTitle = "冠疫苗数";
        // 执行sql和文件存储
        generateSql.execute(templateTitle);
        // 生成excel
        generateSql.generateExcel(templateTitle);
    }

    /**
     * 生成excel文件
     */
    private void generateExcel(String title) {
        String sql = "SELECT parent_code as parentCode,indicator_code as indicatorCode,indicator_name as indicatorName FROM hospital_indicator_info WHERE template_code = ?;";
        new ExcelThread(templateCode, sql, title).start();
    }

    /**
     * 生成单轴的图标信息的sql，只有x轴
     */
    private void runSingle(String[] sonTitleArr, String parentTitle, String type) {

        StringBuilder builder = new StringBuilder();
        // 父节点

        builder.append(insertStr);
        List<String> parentCodeList = new ArrayList<>();
        generateParentAndSon(parentCodeList, builder, parentTitle, sonTitleArr, type);

        resultIndicatorBuilder.append(builder);

        resultRelationBuilder.append(generateRelationSql(parentCodeList));
    }

    /**
     * 生成多轴的图标信息的sql, 图标既有x轴也有y轴 分院区
     */
    private void runMany(String[] sonTitleArr, String[] hospitalList, String title, String type) {
        // 父节点
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(insertStr);
        List<String> parentCodeList = new ArrayList<>();
        for (String hospital : hospitalList) {
            String parentTitle = title + "-" + hospital;
            generateParentAndSon(parentCodeList, stringBuilder, parentTitle, sonTitleArr, type);
        }

        resultIndicatorBuilder.append(stringBuilder);

        resultRelationBuilder.append(generateRelationSql(parentCodeList));
    }

    private void generateParentAndSon(List<String> parentCodeList, StringBuilder stringBuilder, String parentTitle, String[] sonTitleArr, String type) {
        String parentCode = generateCode();
        parentCodeList.add(parentCode);
        stringBuilder.append(generateParentLine(parentCode, parentTitle, type));

        // 使子节点有排序
        int sort = 500;
        // 子节点
        for (String sonTitle : sonTitleArr) {
            stringBuilder.append(generateSonLine(sonTitle, parentCode, sort++, type));
        }
    }

    private void execute(String title) {
        // 指标sql存储到文件、执行指标sql
        new WriteFileThread(replaceComma(resultIndicatorBuilder).toString(), title).start();
        // 关系sql存储到文件、执行关系sql
        new WriteFileThread(replaceComma(resultRelationBuilder).toString(), title + "_relation").start();
    }

    /**
     * 生成关系的sql
     * @param parentCodeList
     * @return
     */
    private StringBuilder generateRelationSql(final List<String> parentCodeList) {
        StringBuilder relation = new StringBuilder();
        relation.append(relationInsertStr);

        for (String parentCode : parentCodeList) {
            String format = String.format(relationStr, parentCode, datetime, datetime);
            relation.append(format);
        }
        return relation;
    }

    /**
     * 写入文件和存入数据库
     *
     * @param result
     * @param title
     */
    private void writeResult(String result, String title) {
        File file = new File(path + title + ".sql");
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(result);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateParentLine(String parentCode, String parentTitle, String type) {
        // 第一层
        Integer level = 1;
        // 无需计算
        Integer tag = 99;
        String itemCode = parentCode;
        return String.format(str, templateCode, level, tag, itemCode, 0, parentCode, parentTitle, indicatorUnit, 500, datetime, type);
    }

    private String generateSonLine(String sonTitle, String parentCode, Integer sort, String type) {
        Integer sonLevel = 2;
        // 需要填写
        Integer sonTag = 1;
        String sonCode = generateCode();
        String itemCode = parentCode;
        return String.format(str, templateCode, sonLevel, sonTag, itemCode, parentCode, sonCode, sonTitle, indicatorUnit, sort, datetime, type);
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private StringBuilder replaceComma(StringBuilder stringBuilder) {
        int i = stringBuilder.lastIndexOf(",");
        return stringBuilder.replace(i, stringBuilder.length(), ";");
    }

    class WriteFileThread extends Thread {
        private final String result;
        private final String title;

        public WriteFileThread(String result, String title) {
            this.result = result;
            this.title = title;
        }

        @Override
        public void run() {
            writeResult(result, title);
            JdbcTemplate.update(result);
        }
    }

    private class ExcelThread extends Thread {
        private final String templateCode;
        private final String sql;
        private final String title;
        public ExcelThread(String templateCode, String sql, String title) {
            this.templateCode = templateCode;
            this.sql = sql;
            this.title = title;
        }

        @Override
        public void run() {
            List<IndicatorDTO> indicatorDTOList = JdbcTemplate.query(sql, new BeanHandler<>(IndicatorDTO.class), templateCode);
            EasyExcel.write(path + title + ".xlsx", IndicatorDTO.class).sheet("模板").doWrite(indicatorDTOList);
        }
    }
}