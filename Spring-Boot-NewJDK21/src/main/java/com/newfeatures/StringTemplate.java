package com.newfeatures;

/**
 *字符串模版
 */
public class StringTemplate {
    public static void main(String[] args) {
        String name = "AOTESAI";
        String info = STR."My name is \{name}";
        assert info.equals("My name is AOTESAI");
    }
}
