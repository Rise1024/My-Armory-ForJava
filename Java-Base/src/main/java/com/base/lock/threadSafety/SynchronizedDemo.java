package com.base.lock.threadSafety;

/**
 * 对象锁和类锁
 */
public class SynchronizedDemo {
    public synchronized void Method_1(int a)
    {
        System.out.println("俺是方法锁"+a);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static synchronized void Method_3(int a)
    {
        System.out.println("俺是类锁"+a);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void Method_2(int a)
    {
        synchronized(this)
        {
            System.out.println("俺是对象锁"+a);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void Method_4(int a)
    {
        synchronized(SynchronizedDemo.class)
        {
            System.out.println("俺是类锁"+a);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo=new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo1=new SynchronizedDemo();

        Thread t1 = new Thread(()->synchronizedDemo.Method_1(1));
        Thread t2 = new Thread(()->synchronizedDemo.Method_1(2));
        t1.start();
        t2.start();
    }
}