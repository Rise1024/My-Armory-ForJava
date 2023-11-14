package com.base.tools.regular;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zds
 * @Description
 * @createTime 2022/6/15 16:41
 */
public class RegularDemo {

    public static void main(String[] args) {
        String testTxt="111.222";
        String pattern = "(\\d{1,3})(\\.(\\d{1,3}))";
        Pattern REGEX_PATTERN = Pattern.compile(pattern);
        Matcher matcher = REGEX_PATTERN.matcher(testTxt);
        /*
           全匹配
         */
//        boolean matches = matcher.matches();//true
//        System.out.println("全匹配结果"+matches);
       /*
          分组匹配
           在java正则表达式中，（ ）是分组的意思，依旧是所谓的捕获组。每一个（ ）代表着一个group，该组是通过从左至右计算其括号来编号。
           在表达式（（A）（B（C））），有四个这样的组：
               1、((A)(B(C)))
               2、(A)
               3、(B(C))
               4、(C)
        */
        while (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0)); //整个组的匹配 (\d{1,3})(\.(\d{1,3}))
            System.out.println(matcher.group(1)); //第一个组 (\d{1,3})
            System.out.println(matcher.group(2)); //第二个组 (\.(\d{1,3}))
            System.out.println(matcher.group(3)); //第三个组 (\d{1,3})
        }
    }
}
