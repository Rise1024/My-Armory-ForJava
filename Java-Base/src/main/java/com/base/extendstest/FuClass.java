package com.base.extendstest;
 
public class FuClass {
    int aa;
 
        //静态变量
        static String i=lcy();
        public static String lcy()
        {
            System.out.println("父类静态变量i="+i);
            return "静态变量";
        }
        //静态语句块
        static {
            System.out.println("父类静态语句块1"+i);
        }
        //静态语句块
        static {
            System.out.println("父类静态语句块2");
        }
        //静态语句块
        static{
            System.out.println("父类静态语句块3");
        }
 
        //非静态变量
        String j=pplcy();
        public String pplcy()
        {
            System.out.println("aa=="+aa);
            aa=11;
            System.out.println("aa=="+aa);
            System.out.println("父类非静态变量");
            return "父类非静态变量";
        }
        //非静态语句块
        {
            System.out.println("父类非静态语句块1");
        }
        //非静态语句块
        {
            System.out.println("父类非静态语句块2");
        }
        //非静态方法
        public void bDisplay(){
            System.out.println("父类非静态方法");
            return ;
        }
        //静态方法
        public static void bTest(){
            System.out.println("父类静态方法");
            return ;
        }
        //构造函数
        public FuClass(){
            System.out.println("父类构造函数");
        }

        public void test1(){
            System.out.println("父类普通方法");
        }
 
 
        public static void main (String args[]) {
            FuClass a=new FuClass();
//            a.bDisplay();
//            a.bTest();
 
        }
 
}