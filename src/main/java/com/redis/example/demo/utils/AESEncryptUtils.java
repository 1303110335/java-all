package com.redis.example.demo.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author: mzd
 * @Date: 2019/10/8 19:51
 */
public class AESEncryptUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AESEncryptUtils.class);

    /**
     * 默认字符集编码
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 填充方式
     */
    private static final String PADDING = "AES/ECB/PKCS5Padding";

    /**
     * 加密算法
     */
    private static final String KEY_ALGORITHM = "AES";


    private AESEncryptUtils() {
    }

    /**
     * 加密
     *
     * @param content    待加密内容
     * @param encryptKey 加密 KEY
     * @return
     */
    public static String encrypt(String content, String encryptKey) {
        if (content == null || encryptKey == null) {
            throw new IllegalArgumentException("加密内容 content 和 encryptKey 不能为 null");
        }

        try {
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(DEFAULT_ENCODING), KEY_ALGORITHM));
            /* 解决Base64加密换行问题 */
            return Base64.encodeBase64String(cipher.doFinal(content.getBytes(DEFAULT_ENCODING)));
        } catch (Exception e) {
            LOGGER.error("加密异常, 加密前内容: {}, 加密key: {}", content, encryptKey, e);
        }

        return null;
    }

    /**
     * 解密
     *
     * @param content    待解密内容
     * @param decryptKey 解密的 KEY
     * @return
     */
    public static String decrypt(String content, String decryptKey) {
        if (content == null || decryptKey == null) {
            throw new IllegalArgumentException("解密内容或解密key不能为null");
        }

        try {
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(DEFAULT_ENCODING), KEY_ALGORITHM));
            byte[] bytes = Base64.decodeBase64(content);
            bytes = cipher.doFinal(bytes);
            return new String(bytes, DEFAULT_ENCODING);
        } catch (Exception e) {
            LOGGER.error("解密异常, 解密前内容: {}, 解密key: {}", content, decryptKey, e);
        }

        return null;
    }

    public static void main(String[] args) {
        String content = "ECA3BNUtfFpaGeRQ2JTyY94elIxLdCCPVpH87oD%2BQZaAfEClKjSGyXTgk0AB0wdIcDqrLZRS7XlYsxS5Ae9K3qdlpFxapCWANlLC2se%2FHkzOLJnQmXLGB%2BCo7D%2Fb0flZHTN98VsDvyET7BnV7cicoPmgX2jwDWz6z%2BQHyZjdsJd%2Fr%2FNeaxJ3jkiV5kPzSL2TXHOF0i%2FrNGVJOyqhZUat58395iN44wgp6qXT6hqZY1cP64womaM%2FuzFDMLpixISl1DZbFMFFJsFoBVbABmZf0Lq8pzJw%2BctRjb%2BIc7XQkZS%2FyobUsuZ%2Bs9oWRgyud8zrEVrDKaDKcHq0k7d89cX8%2BOaKbHUBOn5MLZQqdeWU4XAe35Gq4WoASAwg6SjcJtiy4i8fI6mEZTtM4P0NJBLt4rafWVMd2I0lG3xAvM%2BQLamLxcfEQLn8jMN%2FdA4TvTQ%2BM0x39jIBP7BT00BfC8wmRIaaaWKe%2FY4MSZzfDG17R9VmgZOu5MxmXoByD3GAJsZv7Per8UgoeN5culQEJrVX%2BlQvL7Kh2a93y%2Fr9sa7Vt1dk7oE%2BLwJGX2l9PDa44hy99FhL0FMnHFW8kNoOekRSxg36xgzxKaCBFsmnnJ0Ejx7AIS6yP1ZHRoWveZAqsgEodZp0tV20ymIfS0554eGFJg%2Bw1FlNUZspxe9Su091kU%2FcjA1vf%2BKuDLZX%2Bkq9KFsCEmR3FI%2FBr6dp0YwK7uECbYDjk%2BZkkSnTGASI8XfY5ZBTuhPZ5XWWUU8HgfuaHUFdm0Vymog1YpAZIuuN8ubrMtdJvIU2CmrZKoyRUkV%2BKyV7IN1cHYjFb3JkaLYX7ht8K5%2BnVcY7grsXUmRfLJTLZWSrUEHXgxaT82WDN7ZIquH%2BMq4r1CBHjtS3%2B5ElikxviE%2FpxOkFdomCTb%2FAQQAeLy%2FOhM7qFBQWCwXqR%2FnrDDgJMd5sfLba4vqAXlXNcEeYncobgimYdFDoFVt1%2Bl2UE%2Bc3B12RUkBohsKrvHb9FibCI5gfgUi7dxJGvERTKXCLHwrCPqbQ159KhbPwJuorpcLkmCMVi23XLwxyt4QJ0c0x0pVimxMkEB6Cr%2FxNQh4ksv0EBk%2FiRzD2P7pxYhq2jrXV69NCsy2QrtRsX8%2BrIWnvjPCwcB9WfF7QZ%2BuFZucqCUXsWemTWuh%2FpZ4gddvGAoHg%2FG4X3V8AYMLND2YHf8opujv3fq3wqTer2oUU7AzWlqrMM42mYKwqHqPTTUWEPCm6O%2Fd%2BrfCpN6vahRTsDNY7IqsF7oZAKRXgEMT1%2Fq6ZFoRagZexYnZQBicJWDk6MO%2BIwPAIqRNQa13rgIr%2FJxPl5jvXKRpaLSEpkndVKgY66oY6nU3nxIzLqay83lhs9tYXVY0uV%2FzjoXEPwWbgmXx43fftChKvW46sxIiVh6HjWFoxVCXsXrYm9nALt18JNyoTh0vnsNKyIXT%2FSzvOENpzYqBoQlw1vHiqymSWrWvGGCvaQX%2BlImF5A4HDJXZO6V0MS2FWF9F4oz%2BGX3yjC1Q9W9vaob4%2BoLPsEOT5BRIANKQwvoUmUkC5Kn%2FsVWP%2Fpu353g%2BKqwLhuXnIGMK6or7EzT9bHt8JV%2FGa24bjjlCZFFi5%2BaYYIbUSTmD%2FFwuzoCMsE2s4MS2OjEqh0ug6M6DAqswz9qp8ElsWnxTxhjGHj%2FeeJIVTJrNxWg5FK5SY0qhdQTjxALrY4Jkx5xGyq2jHOsQiFUBHVQUfMNeTk3dbMQMW%2FR1%2FfIksBMGSOEVelkakdfpFbN%2BHnyfJvanaRU%2FL5U1yt98IUubfpWLFBriehu8um2q9C4MOfvnr4PRyxdWwURgPyYOAdf9Wa5lf0wwBLdt2VbaUCyi4frA6QTLaK%2FXr32%2Fte3uc6HwxCad6I8EKQWAF9hF5J%2FoMwZcROt6eifSBhA%2FUnB6LlDGbQq6k1LA1X1TR9z44JFrlEyNE1r3idpYlamkKU%2FhH3%2FeQRHuZf4axMY5dQRoQWe8Uudfe9oVYutnOHYCI12m6JQQJbUcYgtCfMGppwW7zLerx8WA5vqQ5aLLy%2Bw4bwa7m1%2BwXV8fHUry86LROk2Nw%2F7UzlL5J%2BMAP6kvBZ3y69nD%2BXUY%3D";
        String result = decrypt(content, "E896062C5038DA65");
        System.out.println(result);
    }

}
