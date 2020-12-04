/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.annations;

import java.lang.annotation.*;

/**
 *
 * @author xuleyan
 * @version EncryptAndDecryptBody.java, v 0.1 2020-10-16 1:52 下午
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptAndDecryptBody {

    /**
     * 解密key
     */
    String decryptKey() default "E896062C5038DA65";

    /**
     * 加密key
     */
    String encryptKey() default "E896062C5038DA65";
}