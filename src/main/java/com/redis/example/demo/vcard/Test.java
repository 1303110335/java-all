/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.vcard;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author xuleyan
 * @version Test.java, v 0.1 2020-12-15 4:37 下午
 */
@Slf4j
public class Test {

    public static void main(String[] args) {

        String string = "{\"name\":\"苏高标\",\"idcard_type\":\"01\",\"idcard_value\":\"33108120040904401X\",\"request_url\":\"http://10.10.2.231:8072/onilne/getNumberList\",\"med_org_code\":\"123\"}";
        PickupOnlineTestReqDTO pickupOnlineTestReqDTO = JSON.parseObject(string, PickupOnlineTestReqDTO.class);

        List<ExtAccessApplicationFunctionMed> list = new ArrayList<>();
        ExtAccessApplicationFunctionMed extAccessApplicationFunctionMed = new ExtAccessApplicationFunctionMed();
        extAccessApplicationFunctionMed.setMedOrgCode(pickupOnlineTestReqDTO.getMedOrgCode());
        extAccessApplicationFunctionMed.setEncryptionKey("7384D9E3EB91797A99DAC640EB45A3B1");
        extAccessApplicationFunctionMed.setReqUrl(pickupOnlineTestReqDTO.getRequestUrl());
        list.add(extAccessApplicationFunctionMed);

        Test test = new Test();
        test.getRegisteredByThird(list, pickupOnlineTestReqDTO.getName(), pickupOnlineTestReqDTO.getIdCardNo(), pickupOnlineTestReqDTO.getIdCardType(), pickupOnlineTestReqDTO.getMedOrgCode(), true);
    }

    /**
     * 向第三方获取取号列表
     *
     * @return
     */
    private void getRegisteredByThird(List<ExtAccessApplicationFunctionMed> list, String name, String idCardNo, String idCardType, String medOrgCode, boolean errMsg) {
        String requestId = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            ExtAccessApplicationFunctionMed extAccessApplicationFunctionMed = list.get(0);
            String url = extAccessApplicationFunctionMed.getReqUrl();
            String key = extAccessApplicationFunctionMed.getEncryptionKey();
            String time = String.valueOf(System.currentTimeMillis());
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("name", URLEncoder.encode(SecurityUtil.encrypt(name, key), "UTF-8"));
            hashMap.put("idcard_type", URLEncoder.encode(idCardType, "UTF-8"));
            hashMap.put("idcard_value", URLEncoder.encode(SecurityUtil.encrypt(idCardNo, key), "UTF-8"));
            hashMap.put("order_status", URLEncoder.encode("0", "UTF-8"));
            hashMap.put("request_id", URLEncoder.encode(requestId, "UTF-8"));
            hashMap.put("timestamp", URLEncoder.encode(time, "UTF-8"));
            log.info("【取号列表加密后参数】:{}", JSON.toJSONString(hashMap));
            String signPre = SecurityUtil.buildSortJson(hashMap) + key;
            log.info("【md5之前的字符串】:{}", signPre);
            String signature = SecurityUtil.getMd5String32(signPre);
            hashMap.put("signature", signature);
            log.info("【向第三方获取取号列表】最终请求参数：{},url为：{}", JSON.toJSONString(hashMap), url);
            //发起http请求
            HashMap header = new HashMap();
            header.put("med_org_code", medOrgCode);

            //decodeThird(hashMap, key);
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    private void decodeThird(HashMap<String, String> hashMap, String key) throws UnsupportedEncodingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        HashMap<String, String> param = new HashMap<>();
        log.info("【第三方解签开始】");
        log.info("【取号列表解密前参数】:{}", JSON.toJSONString(hashMap));
        String decodeName = URLDecoder.decode(hashMap.get("name"), "UTF-8");
        String name = SecurityUtil.decrypt(decodeName, key);
        param.put("name", name);

        System.out.println(param);


    }
}