package com.security.message_digest;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HMACTest
	//你一定是世界上最快乐的程序员了，不是吗？
	//年轻人不要熬夜
	//再忙也不要忘了运动🏀
{
	public static final String src = "hmac test";

	public static final String HmacSHA256="HmacSHA256";
	public static final String HmacMD5="HmacMD5";


	public static void main(String[] args) 
	{
		jdkHmacMD5();
//		bcHmacMD5();

	}
	
	// 用jdk实现:
	public static void jdkHmacMD5()
	{
		try 
		{
			// 初始化KeyGenerator
			KeyGenerator keyGenerator = KeyGenerator.getInstance(HmacSHA256);
			// 产生密钥
			SecretKey secretKey = keyGenerator.generateKey();
//			 获取密钥
//			byte[] key = secretKey.getEncoded();
			byte[] key = Hex.decodeHex(new char[]{'1','2','3','4','5','6','7','8','9','a','b','c','d','e' });

			long l = System.currentTimeMillis();
			//实例化密钥
			SecretKey restoreSecretKey = new SecretKeySpec(key, HmacSHA256);
			// 实例化MAC
			Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
			// 初始化MAC
			mac.init(restoreSecretKey);
			// 执行摘要
			byte[] hmacMD5Bytes = mac.doFinal(src.getBytes());
			System.out.println("jdk hmacMD5:" + Hex.encodeHexString(hmacMD5Bytes));
			//41748ed75c61cb68badbeb71eb2784f793aef8dbbc3fe7b68df7c2f101d879ee
			//1322445bfc4166f522d824ce77f9978eca5634b3f06dd97a1058b1e8422f99f4
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 用bouncy castle实现:
	public static void bcHmacMD5()
	{
		HMac hmac = new HMac(new MD5Digest());
		// 必须是16进制的字符，长度必须是2的倍数
		hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("123456789abcde")));
		hmac.update(src.getBytes(), 0, src.getBytes().length);
		
		// 执行摘要
		byte[] hmacMD5Bytes = new byte[hmac.getMacSize()];
		hmac.doFinal(hmacMD5Bytes, 0);
		System.out.println("bc hmacMD5:" + org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes));
		
	}

}
