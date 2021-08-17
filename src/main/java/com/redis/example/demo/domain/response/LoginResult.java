/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.domain.response;

import lombok.Data;

/**
 *
 * @author xuleyan
 * @version LoginResult.java, v 0.1 2021-04-11 5:49 下午
 */
@Data
public class LoginResult {
    
    /**
     * 后面登录的token
     */
    private String token = "";
}