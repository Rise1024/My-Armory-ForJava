package com.security.jinzhi;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author zds
 * @Description
 * @createTime 2021/11/15 14:22
 */


public class JinzhiZhuanHuan {

    //进制转换文章参考
    //https://blog.csdn.net/anhuibozhoushatu/article/details/83313257

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * 十六进制字符串转二进制
     *
     * @param hex 十六进制字符串
     * @return
     */
    private static byte[] hex2byte1(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }


    public static byte[] hex2byte2(String hexStr) {
        try {
            return hexStr == null ? new byte[0] : Hex.decodeHex(hexStr.toCharArray());
        } catch (DecoderException e) {
        }
        return new byte[0];
    }

    public static byte[] hex2byte3(String str) { // 字符串转二进制
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;

        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer
                        .decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 二进制转十六进制字符串
     *
     * @param array 二进制数组
     * @return
     */
    public static String byte2hex1(byte[] array) {
        return array == null ? null : new String(Hex.encodeHex(array, false));
    }

    private static String byte2hex2(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    public static String byte2hex3(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }


    /**
     * 生成安全随机数
     *
     * @param size
     * @return
     */

    public static byte[] genSecureRandomByte(int size) {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
        }
        byte[] bytes = new byte[size];
        if (sr != null) {
            sr.nextBytes(bytes);
        }
        return bytes;
    }


    public static void main(String[] args) {

        String str = "hello java";
        String result = byte2hex1(str.getBytes());
        String result1 = byte2hex2(str.getBytes());
        String result2 = byte2hex3(str.getBytes());

        byte[] bytes = hex2byte1(result);
        byte[] bytes1 = hex2byte2(result);
        byte[] bytes2 = hex2byte3(result);

        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);

        System.out.println(new String(bytes));
        System.out.println(new String(bytes1));
        System.out.println(new String(bytes2));

    }


}
