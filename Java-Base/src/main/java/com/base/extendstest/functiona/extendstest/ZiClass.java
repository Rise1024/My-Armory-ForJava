package com.base.extendstest.functiona.extendstest;
 
public class ZiClass extends FuClass{
    int bb;
    //静态变量
    static String a=lcyhh();
    public static String lcyhh()
    {
        System.out.println("子类静态变量");
        return "静态变量";
    }
    //静态语句块
    static {
        System.out.println("子类静态语句块1");
    }
    //静态语句块
    static {
        System.out.println("子类静态语句块2");
    }
    //静态语句块
    static{
        System.out.println("子类静态语句块3");
    }
 
    //非静态变量
    String b=ppplcy();
    public String ppplcy()
    {
        bb=3;
        System.out.println("bb=="+bb);
        System.out.println("子类非静态变量");
        return "子类非静态变量";
    }
    //非静态语句块
    {
        System.out.println("子类非静态语句块1");
    }
    //非静态语句块
    {
        System.out.println("子类非静态语句块2");
    }
    //非静态方法
    public void bDisplay(){
        System.out.println("子类非静态方法");
        return ;
    }
    //静态方法
    public static void bTest(){
        System.out.println("子类静态方法");
        return ;
    }
    //构造函数
    public ZiClass() {
        System.out.println("子类构造函数");
 
    }

    @Override
    public void test1() {
        super.test1();
        System.out.println("子类普通方法");
    }

    /**
     *     父类静态变量、父类静态代码块（从上到下的顺序加载）
     *     子类静态变量、子类静态代码块（从上到下的顺序加载）
     *     父类的非静态变量、父类的非静态代码块（从上到下的顺序加载）
     *     父类的构造方法
     *     子类的非静态变量、子类的非静态代码块（从上到下的顺序加载）
     *     子类的构造方法
     */
    public static void main(String[] args) {
        new ZiClass().test1();
    }
}