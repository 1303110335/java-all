/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.controller;

import com.redis.example.demo.domain.User;
import com.redis.example.demo.domain.param.LoginRequest;
import com.redis.example.demo.domain.response.UserDetail;
import com.redis.example.demo.domain.response.UserResponse;
import com.redis.example.demo.druid.BeanHandler;
import com.redis.example.demo.druid.JdbcTemplate;
import com.redis.example.demo.encrypt.EncryptUtil;
import com.redis.example.demo.utils.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version LoginController.java, v 0.1 2021-04-11 5:46 下午
 */
@RequestMapping("/user/")
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("login")
    public ResponseWrapper<String> login(@RequestBody LoginRequest request) {
        ResponseWrapper<String> loginResult = new ResponseWrapper<String>();
        String username = request.getUsername();
        String password = request.getPassword();

        String sql = "SELECT id, name, age, mobile, password FROM user where name = ?";
        List<User> userList = JdbcTemplate.query(sql, new BeanHandler<>(User.class), username);
        if (CollectionUtils.isEmpty(userList)) {
            loginResult.setSuccess(false);
            loginResult.setErrMsg("失败");
            return loginResult;
        }

        User user = userList.get(0);
        if (user != null && !user.getPassword().equals(password)) {
            loginResult.setSuccess(false);
            loginResult.setErrMsg("失败");
            return loginResult;
        }

        redisTemplate.opsForValue().set(username, password);
        String token = "";
        try {
            token = EncryptUtil.DES_ENCRYPT.encryptBase64(username);
        } catch (Exception e) {
            log.error("token异常", e);
        }
        loginResult.setErrMsg("登录成功");
        loginResult.setData(token);

        return loginResult;
    }

    @RequestMapping("query")
    public ResponseWrapper<UserResponse> queryUser(HttpServletRequest request) {
        ResponseWrapper<UserResponse> response = new ResponseWrapper<>();
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            response.setSuccess(false);
            response.setErrMsg("登录token不能为空");
            return response;
        }

        // 验证token正确性
        String username = null;
        try {
            username = EncryptUtil.DES_ENCRYPT.decryptBase64(token);
        } catch (Exception e) {
            log.error("token异常", e);
            response.setSuccess(false);
            response.setErrMsg("登录token不正确");
            return response;
        }
        String password = redisTemplate.opsForValue().get(username);
        if (StringUtils.isBlank(password)) {
            response.setSuccess(false);
            response.setErrMsg("登录token不正确");
            return response;
        }

        String sql = "SELECT id, name, age, mobile FROM user";
        List<UserDetail> userList = JdbcTemplate.query(sql, new BeanHandler<>(UserDetail.class), null);

        UserResponse userResponse = new UserResponse();
        userResponse.setUserList(userList);
        response.setData(userResponse);
        return response;
    }

    @RequestMapping("/test")
    public UserDetail testGet() {
        UserDetail userDetail = new UserDetail();
        userDetail.setAge(18);
        userDetail.setName("xly");
        return userDetail;
    }



}