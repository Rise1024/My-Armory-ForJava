package com.security.message_digest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * BASE64 + md5
 */
//消息摘要算法优先使用sha256\hmacsha256

public class myDigest {
    public static void main(String[] args)  {
        myDigest my=new myDigest();
        my.testDigest();
    }
    public void testDigest()
    {
        try {
            String myinfo="我的测试信息";
            BASE64Encoder encoder = new BASE64Encoder();
            java.security.MessageDigest alg=java.security.MessageDigest.getInstance("MD5");
//            java.com.security.MessageDigest alg=java.com.security.MessageDigest.getInstance("SHA-1");
            alg.update(myinfo.getBytes());
            byte[] digesta=alg.digest();
            //base64
            String encode = encoder.encode(digesta);
            System.out.println("encode:" + encode);


            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(encode);
            System.out.println("decode:" + new String(bytes));
            //通过某中方式传给其他人你的信息(myinfo)和摘要(digesta) 对方可以判断是否更改或传输正常
            java.security.MessageDigest algb=java.security.MessageDigest.getInstance("MD5");
            algb.update(myinfo.getBytes());
            if (algb.isEqual(bytes,algb.digest())) {
                System.out.println("信息检查正常");
            }
            else
            {
                System.out.println("摘要不相同");
            }
        }
        catch (NoSuchAlgorithmException | IOException ex) {
            System.out.println("非法摘要算法");
        }
    }
    public String byte2hex(byte[] b) //二行制转字符串
    {
        String hs="";
        String stmp="";
        for (int n=0;n<b.length;n++)
        {
            stmp=(Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
            if (n<b.length-1)  hs=hs+":";
        }
        return hs.toUpperCase();
    }
}
