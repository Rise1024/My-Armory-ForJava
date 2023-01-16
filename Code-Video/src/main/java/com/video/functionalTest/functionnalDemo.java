package com.video.functionalTest;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import java.util.function.Consumer;

/**
 * @Auther: zds
 * @Date: 2023/01/11/15:48
 * @Description:
 */
interface test{
    String mothod1(String msg);
}

class test2 {
    public test2() {
    }

    static String mothod2(String msg){
        return "方法引用"+msg;
    }

}

/**
 * 泛型接口
 * @param <T>
 *修饰符 class 类名<代表泛型的变量>
 */
class Generic<T> {
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;
    //泛型构造方法形参key的类型也为T，T的类型由外部指定
    public Generic(T key){
        this.key = key;
    }
    //泛型方法getKey的返回值类型为T，T的类型由外部指定
    public T getKey(){
        return key;
    }
}

/**
        ①普通成员可以使用泛型(属性,方法)
        ②使用泛型的数组，不可以初始化
        ③静态成员不能使用泛型(因为静态是和类相关的，在类加载时，对象还没有创建)
        ④泛型类的类型，是在创建对象时确定的，没有指定类型，默认为Object

        （1）定义和使用含有泛型的类

         //修饰符 class 类名<代表泛型的变量>
         public class Demo<T,R...>{ //表示可以有多个泛型成员

        (2)定义和使用含有泛型的方法

        修饰符 <代表泛型的变量> 返回值类型 方法名（参数） { }
        public <T> void fly(T t) { }
        //注意，下面run方法不是泛型方法，而是使用了类声明的  泛型
        class Dog<T,R>{
            public void run(T t) { }
        }
        (3)定义和使用含有泛型的接口

       //注意，在接口中，它的成员都是静态的，静态成员不能使用泛型
        修饰符 interface 接口名<代表泛型的变量> { }
         public interface MyGenericInterface<E>{}

        (4)泛型通配符
        ①<?>: 支持任意泛型类型
        ②<? extends A>:支持A类以及A类的子类，规定了泛型的上限
        ③<? super A>:支持A类以及A类的父类，不限于直接父类，规定了泛型的下限

**/


public class functionnalDemo {

    public static void main(String[] args) {

        /**
         * lambda
         */
        test test = new test() {
            @Override
            public String mothod1(String msg) {
                return msg+"test impl";
            }
        };
        System.out.println(test.mothod1("hello"));

        //参数->方法实现

        //如果是单行则不需要return关键字
        //如果是多行才需要添加花括号，并使用return
        test test1 = msg -> {
            String s = msg + "test impl";
            return s;
        };
        System.out.println(test1.mothod1("hello"));

        /**
         * 方法引用
         * 类名或对象名，后面跟 :: 然后跟方法名称
         */
        test mothod2 = test2::mothod2;
        String hello = mothod2.mothod1("hello");
        System.out.println(hello);

        /**
         * Consumer{void accept(T t);}
         * 消费式接口	T	void	给定类型为T的参数，执行操作	消费者在中国消费，限定币种只收取软妹币
         *
         * Supplier{T get();}
         * 供给式接口	void	T	获得给定类型T的结果，不传参数	食品工厂生产生产食品，产出商品的类型限定是食品类
         *
         * Predicate{boolean test(T t);}
         * 断言式接口	T	boolean	给定指定类型T的参数，计算出断言式的boolean型结果	类似断言调试，获得的结果非真即假
         *
         * Function<T, R>{R apply(T t);}
         * 函数式接口	T，R	R	给定指定类型T的参数，返回R类型的结果，将函数限定。
         * 类似于加工厂，消费者提供布料，工厂生产指定款式的衣服，加工厂的生产方法是限定的。
         */
       //供给型接口

       /** @FunctionalInterface
        public interface Supplier<T> {
            T get();
        }
        */
        Supplier<Integer> integerSupplier = () -> Integer.valueOf("acb");
       //消费型接口
        /**
         * @FunctionalInterface
         * public interface Consumer<T> {
         *void accept (T t);
         * }
         */
        Consumer<String> consumer = (a) -> System.out.println(a);
       //断言式接口
        /**
         * @FunctionalInterface
         * public interface Predicate<T> {
         *boolean test (T t);
         * }
         */
        Predicate<Integer> tPredicate = (a) -> a > 0;
        //函数式接口
        /**
         * @FunctionalInterface
         * public interface Function<T, R> {
         *R apply (T t);
         * }
         */
        Function<String, Integer> function = (str)->{
            return Integer.parseInt(str);
        };
        //泛型

        Generic generic = new Generic(12);
        Generic generic1 = new Generic("dsad");

    }
}
