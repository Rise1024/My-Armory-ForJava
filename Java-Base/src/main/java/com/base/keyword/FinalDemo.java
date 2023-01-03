package com.base.keyword;

/**
 * @Auther: zds
 * @Date: 2022/11/03/15:02
 * @Description:
 *
 * 1. 数据 声明数据为常量，可以是编译时常量，也可以是在运行时被初始化后不能被改变的常量。
 * 对于基本类型，final 使数值不变；
 * 对于引用类型，final 使引用不变，也就不能引用其它对象，但是被引用的对象本身是可以修改的。
 * 2. 方法 声明方法不能被子类重写。 private 方法隐式地被指定为 final，
 * 如果在子类中定义的方法和基类中的一个 private 方法签名相同，此时子类的方法不是重写基类方法，而是在子类中定义了一个新的方法。
 * 3. 类 声明类不允许被继承
 *
 */
public class FinalDemo {

    final int x = 1;
    // x = 2;  // cannot assign value to final variable 'x'
    final FinalDemo y = new FinalDemo();
}
