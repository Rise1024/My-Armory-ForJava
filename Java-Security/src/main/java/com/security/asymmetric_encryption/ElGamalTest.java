package com.security.asymmetric_encryption;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ElGamalTest {

	public static final String src = "Elgamal test";
	public static void main(String[] args) 
	{
		jdkElgamal();

	}
	
	// jdk实现：“私钥解密、公钥加密” ， 对于：“私钥加密、公钥解密”有问题，因为Elgamal不支持
	public static void jdkElgamal()
	{		
		try 
		{
			// 加入对BouncyCastle支持  
			Security.addProvider(new BouncyCastleProvider());
			
			// 1.初始化发送方密钥
			AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("Elgamal");
			// 初始化参数生成器
			algorithmParameterGenerator.init(256);
			// 生成算法参数
			AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
			// 构建参数材料
			DHParameterSpec dhParameterSpec = (DHParameterSpec)algorithmParameters.getParameterSpec(DHParameterSpec.class);
			// 实例化密钥生成器
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Elgamal");	
			// 初始化密钥对生成器  
			keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			// 公钥
			PublicKey elGamalPublicKey = keyPair.getPublic();
			// 私钥 
			PrivateKey elGamalPrivateKey = keyPair.getPrivate();
			System.out.println("Public Key:" + Base64.encodeBase64String(elGamalPublicKey.getEncoded()));
			System.out.println("Private Key:" + Base64.encodeBase64String(elGamalPrivateKey.getEncoded()));
			
			
			// 2.私钥解密、公钥加密 ---- 加密
			// 初始化公钥  
	        // 密钥材料转换
			X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(elGamalPublicKey.getEncoded());
			// 实例化密钥工厂
			KeyFactory keyFactory2 = KeyFactory.getInstance("Elgamal");
			// 产生公钥
			PublicKey publicKey2 = keyFactory2.generatePublic(x509EncodedKeySpec2);
			// 数据加密 
			// Cipher cipher2 = Cipher.getInstance("Elgamal");
			Cipher cipher2 = Cipher.getInstance(keyFactory2.getAlgorithm()); 
			cipher2.init(Cipher.ENCRYPT_MODE, publicKey2);
			byte[] result2 = cipher2.doFinal(src.getBytes());
			System.out.println("私钥加密、公钥解密 ---- 加密:" + Base64.encodeBase64String(result2));
			
			// 3.私钥解密、公钥加密 ---- 解密
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
			KeyFactory keyFactory5 = KeyFactory.getInstance("Elgamal");
			PrivateKey privateKey5 = keyFactory5.generatePrivate(pkcs8EncodedKeySpec5);
//			Cipher cipher5 = Cipher.getInstance("Elgamal");
			Cipher cipher5 = Cipher.getInstance(keyFactory5.getAlgorithm()); 
			cipher5.init(Cipher.DECRYPT_MODE, privateKey5);
			byte[] result5 = cipher5.doFinal(result2);
			System.out.println("Elgamal 私钥加密、公钥解密 ---- 解密:" + new String(result5));
			
			

			/*	
			// 	私钥加密、公钥解密: 有问题
			// 4.私钥加密、公钥解密 ---- 加密
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("Elgamal");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("Elgamal");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("私钥加密、公钥解密 ---- 加密:" + Base64.encodeBase64String(result));
			
			// 5.私钥加密、公钥解密 ---- 解密
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(elGamalPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("Elgamal");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher = Cipher.getInstance("Elgamal");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			result = cipher.doFinal(result);
			System.out.println("Elgamal 私钥加密、公钥解密 ---- 解密:" + new String(result));
			*/
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
		
}
