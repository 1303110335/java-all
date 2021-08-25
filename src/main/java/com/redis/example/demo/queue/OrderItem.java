/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.queue;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author xuleyan
 * @version OrderItem.java, v 0.1 2021-08-24 3:51 下午
 */
@Data
@ToString
public class OrderItem {

    /**
     * ASC, DESC
     */
    private String orderBy;


}