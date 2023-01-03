package com.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zds
 * @Description
 * @createTime 2022/7/29 17:56
 */
public class StringSub {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt=0;
        String s = br.readLine();
        String substring = s.substring(s.indexOf("\n"));
        char[] chars = substring.toCharArray();
        String sub = s.substring(s.indexOf("\n"),s.length()-1);
        char[] chars1 = sub.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars1.length; j++) {
                if (chars[i]==chars1[j]){
                    cnt++;
                }

            }
        }
        System.out.println(cnt);

    }
}
