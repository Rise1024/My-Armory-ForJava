package com.base.extendstest.functiona.extendstest;

import com.base.extendstest.functiona.extendstest.data.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Auther: zds
 * @Date: 2023/02/07/14:51
 * @Description:反射
 */
public class ReflectClass {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        /**
         * 通过对象.getClass()获得Class对象
         */
        Data data=new Data();
        Class c1 = data.getClass();
        /**
         * .class字面量获取Class对象
         */
        Class c2 = Data.class;
        /**
         * Class类的forName方法获取class对象
         */
        Class c3 = Class.forName("com.base.extendstest.functiona.extendstest.data.Data");

        /**
         * 使用newInstance方法创建对象
         */
        Data data1 = (Data) c3.newInstance();
        /**
         *获取构造方法，创建对象
         */
        Constructor declaredConstructor = c3.getDeclaredConstructor(String.class, Integer.class);
        Data data2 = (Data)declaredConstructor.newInstance("张三", 24);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}
