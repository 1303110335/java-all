/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.druid.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 *
 * @author xuleyan
 * @version IndicatorDTO.java, v 0.1 2020-12-04 5:40 下午
 */
@Data
public class IndicatorDTO {

    @JSONField(name = "parent_code")
    private String parentCode;

    @JSONField(name = "indicator_code")
    private String indicatorCode;

    @JSONField(name = "indicator_name")
    private String indicatorName;
}