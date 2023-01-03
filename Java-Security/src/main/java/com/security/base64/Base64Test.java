package com.security.base64;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

/**
 * base采用ascll编码值取二进制再取二进制然后取十进制对比ascll编码表
 *

 */
public class Base64Test 
{
//（+, /, =)
	public static final String src = "basaaaa";
	public static void main(String[] args) 
	{
		jdkBase64();
		
//		commonsCodesBase64();
//
//		bouncyCastleBase64();
	}
	
	// 用jdk实现
	public static void jdkBase64()
	{
		try 
		{
			String s = java.util.Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
			String s1 = java.util.Base64.getUrlEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
//			BASE64Encoder encoder = new BASE64Encoder();
//			String encode = encoder.encode(src.getBytes());
			System.out.println("base64 encode:" + s);
			System.out.println("base64 url encode:" + s1);

			byte[] decode = java.util.Base64.getDecoder().decode("YmFzYWFhYQ==");

			byte[] decode1 = java.util.Base64.getUrlDecoder().decode("YmFzYWFhYQ==");
			System.out.println("base64 decode:" + new String(decode));

			System.out.println("base64 url decode:" + new String(decode1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 用Apache的common codes实现
	public static void commonsCodesBase64()
	{
		byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
		System.out.println("common codes encode:" + new String(encodeBytes));
		
		byte[] dencodeBytes = Base64.decodeBase64(encodeBytes);
		System.out.println("common codes decode:" + new String(dencodeBytes));
		
	}
	

	// 用bouncy castle实现
	public static void bouncyCastleBase64()
	{
		byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
		System.out.println("bouncy castle encode:" + new String(encodeBytes));
		
		byte[] dencodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
		System.out.println("bouncy castle decode:" + new String(dencodeBytes));
		
	}

}
