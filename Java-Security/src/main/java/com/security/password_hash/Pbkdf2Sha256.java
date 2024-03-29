package com.security.password_hash;

/**
 * @author zds
 * @Description
 * @createTime 2021/9/23 14:21
 */

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * PBKDF2_SHA256加密验证算法
 *
 * @author 慌途L
 */

public class Pbkdf2Sha256 {


    /**
     * 盐的长度
     */
    public static final int SALT_BYTE_SIZE = 16;

    /**
     * 生成密文的长度(例：64 * 4，密文长度为64)
     */
    public static final int HASH_BIT_SIZE = 64 * 4;

    /**
     * 迭代次数(默认迭代次数为 2000)
     */
    private static final Integer DEFAULT_ITERATIONS = 2000;

    /**
     * 算法名称
     */
    private static final String algorithm = "PBKDF2&SHA256";

    /**
     * 获取密文
     * @param password   密码明文
     * @param salt       加盐
     * @param iterations 迭代次数
     * @return
     */
    public static String getEncodedHash(String password, String salt, int iterations) {
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
        }
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(Charset.forName("UTF-8")), iterations, HASH_BIT_SIZE);
        SecretKey secret = null;
        try {
            byte[] hash = keyFactory.generateSecret(keySpec).getEncoded();
        } catch (InvalidKeySpecException e) {
        }

        //使用Base64进行转码密文
//        byte[] rawHash = secret.getEncoded();
//        byte[] hashBase64 = Base64.getEncoder().encode(rawHash);
//        return new String(hashBase64);

        //使用十六进制密文
        return toHex(secret.getEncoded());
    }

    /**
     * 十六进制字符串转二进制
     * @param hex     十六进制字符串
     * @return
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    /**
     * 二进制转十六进制字符串
     * @param array     二进制数组
     * @return
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    /**
     * 密文加盐     (获取‘SALT_BYTE_SIZE’长度的盐值)
     * @return
     */
//    public static String getsalt() {
//        //盐值使用ASCII表的数字加大小写字母组成
//        int length = SALT_BYTE_SIZE;
//        Random rand = new Random();
//        char[] rs = new char[length];
//        for (int i = 0; i < length; i++) {
//            int t = rand.nextInt(3);
//            if (t == 0) {
//                rs[i] = (char) (rand.nextInt(10) + 48);
//            } else if (t == 1) {
//                rs[i] = (char) (rand.nextInt(26) + 65);
//            } else {
//                rs[i] = (char) (rand.nextInt(26) + 97);
//            }
//        }
//        return new String(rs);
//    }

    public static String getsalt()
    {
        SecureRandom secureRandom=new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt.toString();
    }

    /**
     * 获取密文
     * 默认迭代次数：2000
     * @param password      明文密码
     * @return
     */
    public static String encode(String password) {
        return encode(password, getsalt());
    }

    /**
     * 获取密文
     * @param password      明文密码
     * @param iterations    迭代次数
     * @return
     */
    public static String encode(String password, int iterations) {
        return encode(password, getsalt(), iterations);
    }

    /**
     * 获取密文
     * 默认迭代次数：2000
     * @param password      明文密码
     * @param salt          盐值
     * @return
     */
    public static String encode(String password, String salt) {
        return encode(password, salt, DEFAULT_ITERATIONS);
    }

    /**
     * 最终返回的整串密文
     *
     * 注：此方法返回密文字符串组成：算法名称+迭代次数+盐值+密文
     * 不需要的直接用getEncodedHash方法返回的密文
     *
     * @param password   密码明文
     * @param salt       加盐
     * @param iterations 迭代次数
     * @return
     */
    public static String encode(String password, String salt, int iterations) {
        // returns hashed password, along with algorithm, number of iterations and salt
        String hash = getEncodedHash(password, salt, iterations);
        return String.format("%s$%d$%s$%s", algorithm, iterations, salt, hash);
    }

    /**
     * 验证密码
     * @param password       明文
     * @param hashedPassword 密文
     * @return
     */
    public static boolean verification(String password, String hashedPassword) {
        //hashedPassword = 算法名称+迭代次数+盐值+密文;
        String[] parts = hashedPassword.split("\\$");
        if (parts.length != 4) {
            return false;
        }
        //解析得到迭代次数和盐值进行盐值
        Integer iterations = Integer.parseInt(parts[1]);
        String salt = parts[2];
        String hash = encode(password, salt, iterations);
        return hash.equals(hashedPassword);
    }

    public static void main(String[] args) {

        String ewretw = encode("ewretw");

        boolean ewretw1 = verification("ewretw", ewretw);
        System.out.println(ewretw+"______"+ewretw1);
    }
}
