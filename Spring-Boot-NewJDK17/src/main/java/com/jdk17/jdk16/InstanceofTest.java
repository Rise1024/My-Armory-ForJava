package com.jdk17.jdk16;

/**
 * @Auther: zds
 * @Date: 2023/01/14/16:26
 * @Description:
 */
public class InstanceofTest {

    public static void main(String[] args) {
        Object object="acv";
        /**
         * java8
         */
        if (object instanceof String) {
            String s = (String) object;
            if (s.length() > 7) {
                System.out.println(s);
                }
            }

        /**
         * java17
         */
        if (object instanceof String str && str.length() > 7){
            System.out.println(str);
        }
        }
    }

