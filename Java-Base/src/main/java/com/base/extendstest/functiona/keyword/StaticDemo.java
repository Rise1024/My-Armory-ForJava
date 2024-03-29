package com.base.extendstest.functiona.keyword;

/**
 * @Auther: zds
 * @Date: 2022/11/03/15:07
 * @Description:
 * 被static修饰的成员变量和成员方法独立于该类的任何对象。也就是说，它不依赖类特定的实例，被类的所有实例共享。
 * 只要这个类被加载，Java虚拟机就能根据类名在运行时数据区的方法区内定找到他们。
 * 因此，static对象可以在它所属类的任何对象创建之前访问，无需引用任何对象。
 * 类的静态成员概括起来有3种：静态成员变量、静态方法和静态代码块。它们都具有以下一些特点：
 *
 * 在类加载的时候，就进行创建和初始化或执行代码；
 * 它们对于一个类来说，都只有一份；
 * 类的所有实例都可以访问到它们。
 * 静态成员变量：它会在类加载以后进行创建和初始化操作，因为它的唯一性，通常用于对象的数据记录，例如，单例模式下的引用保存。
 *
 * 静态方法：它可以被对象访问，也可以直接通过类名来访问。
 *
 * 静态代码块，采用static修饰，用大括号“{...}”包围起来的代码。这些代码可以使用静态成员变量和方法，它们也是在类加载的时候被调用。
 */
public class StaticDemo {
}
