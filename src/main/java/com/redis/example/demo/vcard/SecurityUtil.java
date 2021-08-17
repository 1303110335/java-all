package com.redis.example.demo.vcard;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author: mzd
 * @Date: 2020/2/10 19:54
 */
public class SecurityUtil {
    /**
     * 加密
     *
     * @param encryptStr 需要加密的参数
     * @param keyStr     加密的key
     * @return
     */
    public static String encrypt(String encryptStr, String keyStr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String result = "";
        //获取key对象
        Key key = new SecretKeySpec(keyStr.getBytes(), "AES");
        //获取Cipher对象,采用ECB工作模式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //初始化cipher，选择加密
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(encryptStr.getBytes("UTF-8"));
        result = Base64.encodeBase64String(bytes);
        return result;
    }

    /**
     * 解密
     *
     * @param decryptStr 需要解密的参数
     * @param keyStr     解密的key
     * @return
     */
    public static String decrypt(String decryptStr, String keyStr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String result = "";
        //获取key对象
        Key key = new SecretKeySpec(keyStr.getBytes(), "AES");
        //获取Cipher对象,采用ECB工作模式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //初始化cipher，选择解密
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(decryptStr));
        result = new String(bytes);
        return result;
    }

    public static String getMd5String32(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuilder buf = new StringBuilder();
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static String buildSortJson(Map<String, String> requestData) {
        StringBuilder sortSb = new StringBuilder();
        List<String> jsonKeys = new ArrayList<>();
        for (String key : requestData.keySet()) {
            jsonKeys.add(key);
        }
        Collections.sort(jsonKeys);
        for (String jsonKey : jsonKeys) {
            if (requestData.get(jsonKey) != null) {
                if (sortSb.length() > 0) {
                    sortSb.append("&");
                }
                sortSb.append(jsonKey).append("=").append(requestData.get(jsonKey));
            }
        }
        return sortSb.toString();
    }


    /**
     * 获取key
     *
     * @return
     */
    public static String getKeyStr() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return parseByte2HexStr(secretKey.getEncoded());
    }


    /**
     * 将二进制数组转换成16进制的字符串
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}
