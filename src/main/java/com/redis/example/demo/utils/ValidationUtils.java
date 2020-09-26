/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.utils;

import com.redis.example.demo.domain.User;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 *
 * @author xuleyan
 * @version ValidationUtils.java, v 0.1 2020-08-03 8:46 下午
 */

public class ValidationUtils {

    /**
     * 使用hibernate的注解来进行验证
     *
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();


    /**
     * 检验参数
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> ResponseWrapper validateParam(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        ResponseWrapper responseWrapper = new ResponseWrapper();
        if (constraintViolations.size() > 0) {
            responseWrapper.setSuccess(false);
            responseWrapper.setErrMsg(constraintViolations.iterator().next().getMessage());
        }
        return responseWrapper;
    }

    public static void main(String[] args) {
        User user = new User();
        ResponseWrapper responseWrapper = ValidationUtils.validateParam(user);
        System.out.println(responseWrapper);

    }
}