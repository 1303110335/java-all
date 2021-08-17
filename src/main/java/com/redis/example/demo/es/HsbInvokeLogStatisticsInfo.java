/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.es;

import lombok.Data;

/**
 *
 * @author xuleyan
 * @version HsbInvokeLogStatisticsInfo.java, v 0.1 2021-05-11 5:59 下午
 */
@Data
public class HsbInvokeLogStatisticsInfo {

    private String apiKey;

    private Long total;

    private Long errorTotal;
    /**
     * 平均响应时间
     */
    private Integer avgRt;
    /**
     * 最小响应时间
     */
    private Integer min;
    /**
     * 最大响应时间
     */
    private Integer max;
}