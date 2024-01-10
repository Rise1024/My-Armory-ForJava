package com.security.base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author zds
 * @Description
 * @createTime 2021/11/30 16:19
 */
public class URLTest {
    public static void main(String[] args) {
        String url = "https://cn-assets.gitee.com/assets/qrcode-weixin-9e7cfb27165143d2b8e8b268a52ea822.jpg";
        test(url);
    }

    public static void test(String str) {
        try {
            String encode = URLEncoder.encode(str, "UTF-8");
            System.out.println(encode);
            String decode = URLDecoder.decode(encode, "UTF-8");
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
        }


    }
}
