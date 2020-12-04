package com.redis.example.demo.encrypt;

import com.redis.example.demo.encrypt.EncryptEnum.AseEnum;
import com.redis.example.demo.encrypt.EncryptEnum.Dec3Enum;
import com.redis.example.demo.encrypt.EncryptEnum.DecEnum;
import com.redis.example.demo.encrypt.EncryptEnum.MDEnum;
import com.redis.example.demo.encrypt.EncryptEnum.RSAEnum;
import com.redis.example.demo.encrypt.EncryptEnum.ShaEnum;
import com.redis.example.demo.encrypt.encryptImp.ICipherEncrypt;
import com.redis.example.demo.encrypt.encryptImp.IMessageDigest;
import com.redis.example.demo.encrypt.encryptTypeImpl.AesKgenUtil;
import com.redis.example.demo.encrypt.encryptTypeImpl.AesUtil;
import com.redis.example.demo.encrypt.encryptTypeImpl.Des3Util;
import com.redis.example.demo.encrypt.encryptTypeImpl.DesUtil;
import com.redis.example.demo.encrypt.encryptTypeImpl.MDUtil;
import com.redis.example.demo.encrypt.encryptTypeImpl.RsaUtil;
import com.redis.example.demo.encrypt.encryptTypeImpl.ShaUtil;

/**
 * 加密算法工具类
 */
public class EncryptUtil {
	public static String MD_SLAT = "797bv851O7pU04yu001aZ57Vb36Ai4810786Khe0H59U85Z2LR61o5201zam5DdxR7084A9G71";
	
	public static String SHA_SLAT = "797bv851O7pU04yu001aZ57Vb36Ai4810786Khe0H59U85Z2LR61o5201zam5DdxR7084A9G72";
	
	public static String AES_SLAT = "797bv851O7pU04yu001aZ57Vb36Ai4810786Khe0H59U85Z2LR61o5201zam5DdxR7084A9G73";
	
	public static String DES_SLAT = "797bv851O7pU04yu001aZ57Vb36Ai4810786Khe0H59U85Z2LR61o5201zam5DdxR7084A9G74";
	
	public static String DES3_SLAT = "797bv851O7pU04yu001aZ57Vb36Ai4810786Khe0H59U85Z2LR61o5201zam5DdxR7084A9G74";
	
	public static String RSA_SLAT = "797bv851O7pU04yu001aZ57Vb36Ai4810786Khe0H59U85Z2LR61o5201zam5DdxR7084A9G75";
	
	public final static IMessageDigest<MDEnum> MD_ENCRYPT = new MDUtil(MDEnum.MD5);
	
	public final static IMessageDigest<ShaEnum> SHA_ENCRYPT = new ShaUtil(ShaEnum.SHA256);	
	
	public final static ICipherEncrypt<AseEnum> AES_ENCRYPT = new AesUtil(AseEnum.CBC_NO_PADDING);
	
	public final static ICipherEncrypt<DecEnum> DES_ENCRYPT = new DesUtil(DecEnum.CBC_NO_PADDING);
	
	public final static ICipherEncrypt<Dec3Enum> DES3_ENCRYPT = new Des3Util(Dec3Enum.CBC_NO_PADDING);
	
	public final static ICipherEncrypt<AseEnum> AES_KGEN_ENCRYPT = new AesKgenUtil(AseEnum.CBC_NO_PADDING);
	
	public final static ICipherEncrypt<RSAEnum> RSA_ENCRYPT = new RsaUtil(RSAEnum.ECB_PKCS1PADDING);
}
