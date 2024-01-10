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

    public static byte[] hex2byte2(String hexStr) {
        try {
            return hexStr == null ? new byte[0] : Hex.decodeHex(hexStr.toCharArray());
        } catch (DecoderException e) {
        }
        return new byte[0];
    }

    /**
     *  创建一个字节数组 binary，其长度为输入十六进制字符串长度的一半。因为两个十六进制字符对应一个字节，所以数组长度是字符串长度的一半。
     *  然后使用 Integer.parseInt 方法将这个十六进制数值转换为对应的整数。(byte) 进行强制类型转换，
     *  将得到的整数转换为字节，并将其存储在字节数组的相应位置上。
     * @param hex
     * @return
     */
    private static byte[] hex2byte1(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }


    /**
     * 首先将两个字符作为一个十六进制字符串进行解析。
     * 将这个十六进制字符串转换为对应的整数，
     * 将其转换为整数值，然后强制类型转换为字节，并将其存储在字节数组中。
     * @param str
     * @return
     */
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

    public static String byte2hex2(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static String hex2byte4(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);
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
        //10进制转2进制
        System.out.println(Integer.toBinaryString(100));
        //10进制转8进制
        System.out.println( Integer.toOctalString(100));
        //10进制转16进制
        System.out.println( Integer.toHexString(100));
        //10进制转r进制
        System.out.println(Integer.toString(100, 16));

        //其他进制转10进制
        System.out.println(Integer.parseInt("64", 16));
        System.out.println(Integer.parseInt("144", 8));
        System.out.println(Integer.parseInt("1100100", 2));


        /**
         * 二进制数组转十六进制字符串
         */
        String str = "hello java";
        String result = byte2hex1(str.getBytes());
        String result1 = byte2hex2(str.getBytes());
        String result2 = byte2hex3(str.getBytes());
        String result3 = hex2byte4(str.getBytes(),16);
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        byte[] bytes = hex2byte1(result);
        byte[] bytes1 = hex2byte2(result);
        byte[] bytes2 = hex2byte3(result);
        System.out.println(new String(bytes));
        System.out.println(new String(bytes1));
        System.out.println(new String(bytes2));




    }


}
