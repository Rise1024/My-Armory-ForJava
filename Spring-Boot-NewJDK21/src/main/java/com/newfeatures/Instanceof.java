package com.newfeatures;

import java.util.Map;

/**
 *在JDK17我们知道一个对象可以使用record来声明
 *
 */
public class Instanceof {


    record Point(int x, int y) {
    }

    //在JDK21之前instanceof使用我们需要调用point对象
    static void printSum1(Object obj) {
        if (obj instanceof Point p) {
            int x = p.x();
            int y = p.y();
            System.out.println(x + y);
        }
    }
  //在JDK21可以直接这样使用
    static void printSum(Object obj) {
        if (obj instanceof Point(int x, int y)) {
            System.out.println(x + y);
        }
    }
    public static void main(String[] args) {
        Point point = new Point(1, 2);
        printSum(point);

/**
 * 在JDK21前 在使用foreach遍历的时候,必须把v也命名 这样会占用一个名称 而且IDEA也会提示这个变量未使用
 * 但在JDK21后 就可以使用_来命名了
 */
        var map = Map.of("one", "1", "two", "2");
        map.forEach((k, v) -> {
            System.out.println(k);
        });


        var map1 = Map.of("one", "1", "two", "2");
        map.forEach((k, _) -> {
            System.out.println(k);
        });

    }

}
