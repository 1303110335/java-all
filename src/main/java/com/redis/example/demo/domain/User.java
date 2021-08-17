/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.util.function.Function;

/**
 *
 * @author xuleyan
 * @version User.java, v 0.1 2020-09-02 4:41 下午
 */
@Slf4j
@Data
public class User implements Comparable<User> {

    private Integer id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    private Integer age;

    private String mobile;

    private String password;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(User other) {
        if (other == null) {
            return 1;
        }
        if (this.getAge().equals(other.getAge())) {
            return 0;
        }
        // 注意这里的this是新增的节点，other是老的节点，比较之后，返回1，则放在后面，由于PriorityQueue是最小堆
        return this.getAge() > other.getAge() ? 1 : -1;
    }
}