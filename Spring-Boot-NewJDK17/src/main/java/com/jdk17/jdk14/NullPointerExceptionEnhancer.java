package com.jdk17.jdk14;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
