/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.redis.example.demo.druid.domain.IndicatorDTO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version Test.java, v 0.1 2020-12-04 5:46 下午
 */
public class Test {

    public static void main(String[] args) {
        String path = "target/threads/";
        List<IndicatorDTO> indicatorDTOList = new ArrayList<>();
        IndicatorDTO indicatorDTO = new IndicatorDTO();
        indicatorDTO.setIndicatorCode("123");
        indicatorDTO.setIndicatorName("name");
        indicatorDTO.setParentCode("444");
        indicatorDTOList.add(indicatorDTO);
        String fileName = path + "write" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, IndicatorDTO.class).sheet("模板").doWrite(indicatorDTOList);


//        // 写法2
//        String fileName = path + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = null;
//        try {
//            excelWriter = EasyExcel.write(fileName, IndicatorDTO.class).build();
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(indicatorDTOList, writeSheet);
//        } finally {
//            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//        }
    }
}