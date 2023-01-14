package com.jdk17.jdk10;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: zds
 * @Date: 2023/01/14/15:58
 * @Description:
 * 在 Java 10 之前版本中，当我们定义局部变量时，
 * 需要在赋值的左侧提供显式类型，
 * 并在赋值的右边提供实现类型：
 * 在 Java 10 以后，可以用下面的方式：
 */
public class VarTest {

    public static void main(String[] args) {
        String at="hello";
         var a="hello";
         var b="你好";
         var c=1;
         var strings = Arrays.asList(a);
        strings.stream().forEach(System.out::println);
    }
}
