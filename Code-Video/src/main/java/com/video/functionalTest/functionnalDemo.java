package com.video.functionalTest;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import org.aopalliance.intercept.MethodInterceptor;

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
//       test2::new;
        test mothod2 = test2::mothod2;
        String hello = mothod2.mothod1("hello");
        System.out.println(hello);



    }
}
