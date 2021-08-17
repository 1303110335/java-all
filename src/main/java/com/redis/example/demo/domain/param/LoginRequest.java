/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.domain.param;

import lombok.Data;

/**
 *
 * @author xuleyan
 * @version LoginRequest.java, v 0.1 2021-04-11 5:47 下午
 */
@Data
public class LoginRequest {

    private String username;

    private String password;
}