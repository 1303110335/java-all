/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.domain.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author xuleyan
 * @version UserDetail.java, v 0.1 2021-04-11 7:08 下午
 */
@Data
public class UserDetail {

    private Integer id;

    private String name;

    private Integer age;

    private String mobile;
}