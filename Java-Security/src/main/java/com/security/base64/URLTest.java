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

    }

    public static void test(){

        String str="";
        try {
            String encode = URLEncoder.encode(str, "UTF-8");
            String decode = URLDecoder.decode(encode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }


    }
}
