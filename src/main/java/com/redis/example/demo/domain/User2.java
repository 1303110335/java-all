/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author xuleyan
 * @version User.java, v 0.1 2020-09-02 4:41 下午
 */
public class User2 {

    @NotBlank(message = "姓名不能为空")
    private String name2;

    private Integer age2;

    public User2() {
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Integer getAge2() {
        return age2;
    }

    public void setAge2(Integer age2) {
        this.age2 = age2;
    }

    @Override
    public String toString() {
        return "User2{" +
                "name2='" + name2 + '\'' +
                ", age2=" + age2 +
                '}';
    }
}