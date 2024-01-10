package com.security.base64;
import org.apache.commons.codec.binary.Base64;
import java.nio.charset.StandardCharsets;
/**
 * base采用ascll编码值取二进制再取二进制然后取十进制对比ascll编码表
 */
public class Base64Test {

//    public static final String src = "basaaaa";
    public static final String src = "https://cn-assets.gitee.com/assets/qrcode-weixin-9e7cfb27165143d2b8e8b268a52ea822.jpg";


    public static void main(String[] args) {
        jdkBase64();
//		commonsCodesBase64();
//		bouncyCastleBase64();
    }

    // 用jdk实现
    public static void jdkBase64() {
        try {
            String s = java.util.Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
            /**
             *  getUrlEncoder()方法返回一个URL安全的Base64编码器。
             *  URL安全的Base64编码器会使用URL和文件名安全字符替换标准Base64字符集中的+和/字符。
             *  替换后的字符为-和_，而且省略了填充字符=。
             *  适用于在URLs、文件名或其他需要URL安全字符的环境下进行Base64编码。
             */
            String s1 = java.util.Base64.getUrlEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
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
    public static void commonsCodesBase64() {
        byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
        System.out.println("common codes encode:" + new String(encodeBytes));

        byte[] dencodeBytes = Base64.decodeBase64(encodeBytes);
        System.out.println("common codes decode:" + new String(dencodeBytes));

    }


    // 用bouncy castle实现
    public static void bouncyCastleBase64() {
        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("bouncy castle encode:" + new String(encodeBytes));

        byte[] dencodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
        System.out.println("bouncy castle decode:" + new String(dencodeBytes));

    }

}
