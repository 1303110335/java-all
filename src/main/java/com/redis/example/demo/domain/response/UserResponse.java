/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.domain.response;

import lombok.Data;

import java.util.List;

/**
 *
 * @author xuleyan
 * @version UserResponse.java, v 0.1 2021-04-11 6:16 下午
 */
@Data
public class UserResponse {

    private List<UserDetail> userList;
}