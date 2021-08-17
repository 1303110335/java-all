/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.druid.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author xuleyan
 * @version Account.java, v 0.1 2020-09-24 10:01 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private String address;
    private Integer goods;
}