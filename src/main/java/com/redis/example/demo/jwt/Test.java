/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.jwt;

import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * 尝试使用jwt
 * JSON Web Token 是什么？
 * JSON Web Token (JWT)是一个开放标准(RFC 7519)，它定义了一种紧凑的、自包含的方式，用于作为JSON对象在各方之间安全地传输信息。
 * 该信息可以被验证和信任，因为它是数字签名的。
 *
 *
 * @author xuleyan
 * @version Test.java, v 0.1 2021-08-18 6:34 上午
 */
public class Test {

    public static final String USER_NAME = "xuleyan";

    public static void main(String[] args) {
        SignatureAlgorithm signatureAlgorithm = getSignatureAlgorithm();
        SecretKey key = getKey();

        // 设置启动时间和过期时间
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 300000;
        Date now = new Date(nowMillis);
        Date expDate = new Date(expMillis);

        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        map.put("username", "xly");
        map.put("password", "123456");

        JwtBuilder builder = Jwts.builder();
        builder.setId("1").setSubject("jwt").setIssuer(USER_NAME).setIssuedAt(now).signWith(signatureAlgorithm, key).setExpiration(expDate).addClaims(map);

        String token = builder.compact();
        System.out.println("token = " + token);
        Claims claims = null;
        // 解析token内容
        try {
            // 签名必须和内容匹配才能解析
            // 且不能过期
            claims = parseJWT(token);
            Object username= claims.get("username");
            Object password= claims.get("password");
            System.out.println("*****************************************************************");
            System.out.println(username);
            System.out.println(password);
            System.out.println("*****************************************************************");
        } catch (Exception e) {
            System.out.println("解析异常" + e);
        }

    }

    /**
     * 解析JWT字符串获取Claims容器
     * @param jwt
     * @return
     */
    private static Claims parseJWT(String jwt) {
        SecretKey secretKey = getKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

    /**
     * 获取加密用的密钥
     * @return
     */
    private static SecretKey getKey() {
        byte[] encodedKey = Base64.decodeBase64(USER_NAME);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "");
    }

    /**
     * 获取加密算法
     * @return
     */
    private static SignatureAlgorithm getSignatureAlgorithm() {
        return SignatureAlgorithm.HS256;
    }
}