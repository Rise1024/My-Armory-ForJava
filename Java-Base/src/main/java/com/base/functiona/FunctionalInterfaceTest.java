package com.base.functiona;

/**
 * @author zds
 * @Description  函数式接口
 * @createTime 2021/7/8 17:42
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class FunctionalInterfaceTest {

    //Predicate<T> 断言型接口：将满足条件的字符串放入集合
    public static List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                newList.add(s);
            }
        }
        return newList;
    }
    public static void testPredicate() {
        List<String> list = Arrays.asList("hello", "java8", "function", "predicate");
        List<String> newList = filterStr(list, s -> s.length() > 5);
        for (String s : newList) {
            System.out.println(s);
        }
    }

    // Function<T, R> 函数型接口：处理字符串
    public static String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    public static void testFunction() {
        String str1 = strHandler("测试内置函数式接口", s -> s.substring(2));
        System.out.println(str1);

        String str2 = strHandler("abcdefg", s -> s.toUpperCase());
        System.out.println(str2);
    }

    //Supplier<T> 供给型接口 :产生指定个数的整数，并放入集合
    public static List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }


    public static void testSupplier() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));

        numList.sort(Comparator.comparing(Integer::intValue));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    //Consumer<T> 消费型接口 :修改参数
    public static void modifyValue(Integer value, Consumer<Integer> consumer) {
        consumer.accept(value);
    }

    public static void testConsumer() {
        modifyValue(3, s -> System.out.println(s * 3));
    }

    @FunctionalInterface
    public interface Test<T> {
        T get(T t);
    }
    /**
     *
     * @param 'Lambda'操作
     * @param ' 参数'
     * @return
     */
    public static Integer getInteger(Test<Integer> test,Integer integer) {
        return test.get(integer);
    }


    public static void main(String[] args) {

        getInteger(e->(e+1),1 );
/**
 * lambad表达式：参数 -> 操作
 */
/**
 *  函数接口 Comparator和Runnable、Callable
 */


       /*Java8 内置的四大核心函数式接口
         Consumer<T> : 消费型接口  void accept(T t); BiConsumer<T,U> 两个参数
         Supplier<T> : 供给型接口   T get();
        Function<T, R> : 函数型接口  R apply(T t);  BiFunction<T,U,R>
         Predicate<T> : 断言型接口   boolean test(T t); BiPredicate<T,U>*/
        /**
         * 消费型接口   s -> System.out.println(s * 3)   参数T  无返回值 void accept(T t);
         */
        testConsumer();
        /**
         * 供给型接口   () -> (int) (Math.random() * 100) 无参数  有返回值T  T get();
         */
        testSupplier();
        /**
         * 函数型接口    s -> s.toUpperCase() 参数T  返回值R    R apply(T t);
         */
        testFunction();
        /**
         * 断言型接口    s -> s.length() > 5 参数T  返回值boolean   boolean test(T t);
         */
        testPredicate();
        /**
        * 测略模式优化if else
        */


        new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        };


    }
}