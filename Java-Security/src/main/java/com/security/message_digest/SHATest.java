package com.security.message_digest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;


public class SHATest {
    public static final String src = "sha test";
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        jdkSHA();
        bcSHA();
        ccSHA();
    }

    // 用jdk实现:SHA-256、SHA-1
    public static void jdkSHA() {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA256);
            System.out.println("jdk sha-1:" + Hex.encodeHexString(md.digest(src.getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用bouncy castle实现:SHA-1 、SHA-256
    public static void bcSHA() {
//        Digest digest = new SHA1Digest();
        Digest digest = new SHA256Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] sha1Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha1Bytes, 0);
        System.out.println("bc sha-1:" + org.bouncycastle.util.encoders.Hex.toHexString(sha1Bytes));
    }


    // 用common codes实现实现:SHA-1 、SHA-256
    public static void ccSHA() {
        System.out.println("common codes SHA1 :" + DigestUtils.sha1Hex(src.getBytes()));
        System.out.println("common codes SHA256 :" + DigestUtils.sha256Hex(src.getBytes()));
    }


}
