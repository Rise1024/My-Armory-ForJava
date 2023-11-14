package com.base.tools.basicType;

/**
 * @author zds
 * @Description  java基本类型 线程安全
 * @createTime 2022/3/24 15:25
 */

import java.util.Objects;

/**
 * == /equals/hashcode
 */
public class CompareWithType {

    private static void test1() {
        String a = "a" + "b" + 1;
        String b = "ab1";
        System.out.println(a == b);
        Object o;
    }

    /**
     * 对于引用类型来说,通常使用equals或者重写equals方法
     *  重写equals方法通常方式：
     *  1、判断对象是否相等
     *  2、判断是否是统一class
     *  3、判断对象的值
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        if (obj instanceof Long) {
            return Objects.equals(1l, ((Long) obj).longValue());
        }
        return false;
    }

    public static void main(String[] args) {
        /**
         * 当“==”匹配的时候，其实就是对比两个内存单元的内容是否一样。
         * 如果是原始类型byte、boolean、short、char、int、long、float、double，就是直接比较它们的值
         * 如果是引用（Reference），比较的就是引用的值，“引用的值”可以被认为是对象的逻辑地址
         * 如果两个引用发生“==”操作，就是比较相应的两个对象的地址值是否一样,如果两个引用所保存的对象是同一个对象，则返回true，否则返回false
         *
         */
//        test1();

        /**
         * 1，两个对象，用==比较比较的是地址，需采用equals方法（可根据需求重写）比较。
         * 2，重写equals()方法就重写hashCode()方法。
         * 3，一般相等的对象都规定有相同的hashCode。
         * 4，String类重写了equals和hashCode方法，比较的是值。
         * 5，重写hashcode方法为了将数据存入HashSet/HashMap/Hashtable（可以参考源码有助于理解）类时进行比较 重点
         */

int c=0;
        System.out.println(++c);
        System.out.println(c++);

    }

}
