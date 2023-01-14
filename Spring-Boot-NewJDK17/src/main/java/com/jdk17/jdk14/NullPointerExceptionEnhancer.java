package com.jdk17.jdk14;

import java.util.ArrayList;

/**
 * NullPointerException问题
 */
public class NullPointerExceptionEnhancer {
    public static void main(String[] args) {
        test1();
    }

    private static final void test1() {
        try {
            var a = new ArrayList<Integer>();
            a.add(null);
            System.out.println(a.get(0).longValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
