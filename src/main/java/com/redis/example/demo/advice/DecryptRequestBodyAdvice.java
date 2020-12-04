/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.advice;

import com.redis.example.demo.annations.EncryptAndDecryptBody;
import com.redis.example.demo.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author xuleyan
 * @version DecryptRequestBodyAdvice.java, v 0.1 2020-10-16 1:43 下午
 */
@ControllerAdvice
@Slf4j
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getMethod().isAnnotationPresent(EncryptAndDecryptBody.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        log.info("beforeBodyRead >> ");
        String content = IOUtils.toString(inputMessage.getBody(), StandardCharsets.UTF_8);
        EncryptAndDecryptBody methodAnnotation = parameter.getMethodAnnotation(EncryptAndDecryptBody.class);
        String decryptKey = methodAnnotation.decryptKey();
        String result = content;
        try {
            result = EncryptUtil.decrypt(content, decryptKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DecryptHttpInputMessage(inputMessage, result);
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("afterBodyRead >> ");
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    class DecryptHttpInputMessage implements HttpInputMessage {

        private HttpHeaders headers;

        private InputStream body;

        public DecryptHttpInputMessage(HttpInputMessage inputMessage, String result) {
            this.headers = inputMessage.getHeaders();
            this.body = IOUtils.toInputStream(result, StandardCharsets.UTF_8);
        }

        @Override
        public InputStream getBody() throws IOException {
            return this.body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.headers;
        }
    }
}