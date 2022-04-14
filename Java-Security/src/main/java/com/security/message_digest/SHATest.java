package com.security.message_digest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;


public class SHATest 
{
	public static final String src = "sha test";
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		jdkSHA1();
		SHA1Fork();
//		bcSHA1();
//		bcSHA224();
//		bcSHA224b();
//		generateSha256();
//		ccSHA1();

	}
	
	// 用jdk实现:SHA1
	public static void jdkSHA1()
	{
		try 
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(src.getBytes());
			System.out.println("jdk sha-1:" + Hex.encodeHexString(md.digest()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String SHA1Fork() {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = src.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			strDes = bytes2Hex(md.digest(bt)); // to HexString
		} catch (NoSuchAlgorithmException e) {
		}
		System.out.println(strDes);
		return strDes;
	}

	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
	
	// 用bouncy castle实现:SHA1 
	public static void bcSHA1()
	{
		
		Digest digest = new SHA1Digest();
		digest.update(src.getBytes(), 0, src.getBytes().length );
		byte[] sha1Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(sha1Bytes, 0);
		System.out.println("bc sha-1:" + org.bouncycastle.util.encoders.Hex.toHexString(sha1Bytes));		
	}
	

	// 用bouncy castle实现:SHA224 
	public static void bcSHA224()
	{
		
		Digest digest = new SHA224Digest();
		digest.update(src.getBytes(), 0, src.getBytes().length );
		byte[] sha224Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(sha224Bytes, 0);
		System.out.println("bc sha-224:" + org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));		
	}
	
	// 用bouncy castle与jdk结合实现:SHA224 
	public static void bcSHA224b()
	{
		
		try 
		{
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest md = MessageDigest.getInstance("SHA224");
			md.update(src.getBytes());
			System.out.println("bc and JDK sha-224:" + Hex.encodeHexString(md.digest()));
			char[] a={'a','b'};
			byte[] dsfasds = Hex.decodeHex(a);

		} catch (Exception e) {
		}
	}
	public static void generateSha256() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(src.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        System.out.println("Sha256 hash: " + bigInt.toString(16));
	}
	
	// 用common codes实现实现:SHA1
	public static void ccSHA1()
	{
		System.out.println("common codes SHA1 - 1 :" + DigestUtils.sha1Hex(src.getBytes()));
		System.out.println("common codes SHA1 - 2 :" + DigestUtils.sha1Hex(src));
	}
	
	

}
