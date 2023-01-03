package com.question;

/**
 * @author zds
 * @Description
 * @createTime 2022/8/3 15:13
 */
public class StringTest {

    private static void test(String a){
        a="1111";
    }

    public static void main(String[] args) {
//        final String a="dasfds";
        String a=new String("dasfds");
        test(a);
        System.out.println(a);
    }
}
