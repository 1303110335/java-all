/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author xuleyan
 * @version DateUtil.java, v 0.1 2020-09-07 5:48 下午
 */
public class DateTimeUtils {

    // 日期格式
    public static final String ACCOUNT_DATETIME_PATTERN = "yy-MM-dd HH:mm";
    public static final String NORMAL_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String NORMAL_DATETIME_PATTERN_EXT = "MM-dd HH:mm";
    public static final String NORMAL_TIME_PATTERN = "HH:mm:ss";
    public static final String NORMAL_TIME_MINUTE = "HH:mm";
    public static final String NORMAL_DATETIME_MILLI_PATTERN = "yyyyMMddHHmmssSSS";
    public static final String LL_DATETIME_PATTERN = "yyyyMMddHHmmss";
    public static final String NORMAL_DATE_PATTERN = "yyyy-MM-dd";
    public static final String YEAR_MONTH_PATTERN = "yyyy-MM";
    public static final String MONTH_PATTERN = "MM";
    public static final String DAILY_DATE_PATTERN = "yyyyMMdd";
    public static final String CHINESE_DATE_PATTERN = "MM月dd日";
    public static final String CHINESE_YEAR_DATE_PATTERN = "yyyy年MM月dd日";
    public static final String CHINESE_YEAR_DATE_PATTERN_MINUTE = "yyyy年MM月dd日 HH:mm";
    public static final String NORMAL_YEAR_YY = "yyyy";

    public static void main(String[] args) {
        // 处理时间为当前周周一日期，以获取到最新数据

        // 上周1到上周日
        System.out.println(getDateByWeek(-7) + "-" + getDateByWeek(-1));
        // 上个月第一天到最后一天
        System.out.println(getFirstDayOfLastMonth() + "-" + getLastDayOfLastMonth());
        // 上个季度第一天和最后一天
        Map<Object, Object> condition = new HashMap<>();
        setLastQuarterRange(condition);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String startTime = format.format(condition.get("startTime"));
        String stopTime = format.format(condition.get("stopTime"));
        System.out.println(startTime + "-" + stopTime);

    }

    /**
     * 获取上周开始时间和结束时间
     *
     * @param condition
     * @return
     */
    public static void setLastWeekRange(Map<Object, Object> condition) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        condition.put("startTime", calendar.getTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        condition.put("stopTime", calendar.getTime());
    }

    /**
     * 获取上月开始时间和结束时间
     *
     * @param condition
     * @return
     */
    public static void setLastMonthRange(Map<Object, Object> condition) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        condition.put("startTime", calendar.getTime());
        calendar.add(Calendar.DAY_OF_WEEK, 7);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        condition.put("stopTime", calendar.getTime());
    }

    /**
     * 获取上一年开始时间和结束时间
     *
     * @param condition
     * @return
     */
    public static void setLastYearRange(Map<Object, Object> condition) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), 0, calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.YEAR, -1);
        condition.put("startTime", calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.SECOND, -1);
        condition.put("stopTime", calendar.getTime());
    }


    /**
     * 获取上季度开始时间和结束时间
     *
     * @param condition
     * @return
     */
    public static void setLastQuarterRange(Map<Object, Object> condition) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                calendar.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                calendar.set(Calendar.MONTH, 3);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                calendar.set(Calendar.MONTH, 6);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                calendar.set(Calendar.MONTH, 9);
            }
            calendar.set(Calendar.DATE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH, -3);
        condition.put("startTime", calendar.getTime());
        calendar.add(Calendar.MONTH, 3);
        calendar.set(Calendar.SECOND, -1);
        condition.put("stopTime", calendar.getTime());
    }
    /**
     * 获取上周的日子
     * @param days
     * @return
     */
    public static String getDateByWeek(int days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.add(Calendar.DAY_OF_WEEK, days);
        String week = format.format(c.getTime());
        return week;
    }

    /**
     * 获取上周的日子
     * @return
     */
    public static String getFirstDayOfLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        String week = format.format(c.getTime());
        return week;
    }

    /**
     * 获取上周的日子
     * @return
     */
    public static String getLastDayOfLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        String week = format.format(c.getTime());
        return week;
    }

    public static Date parseToDate(Date date,String type) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(type);//CHINESE_YEAR_DATE_PATTERN "yyyy年MM月dd日"
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将时间字符串格式转换成date格式
     * @param date
     * @param type
     * @return
     */
    public static Date parse(String date, String type) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * @param date 日期
     * @param x         小时
     * @return
     */
    public static Date addDate(Date date, int x) {
        //24小时制

        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, x);
        date = cal.getTime();
        cal = null;
        return date;
    }
}