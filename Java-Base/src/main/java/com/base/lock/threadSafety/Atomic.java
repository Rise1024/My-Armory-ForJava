package com.base.lock.threadSafety;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author zds
 * @Description 原子类
 * @createTime 2022/3/24 17:28
 *
 * Java和C++语言的一个重要区别就是Java中我们无法直接操作一块内存区域，不能像C++中那样可以自己申请内存和释放内存。
 * Java中的Unsafe类为我们提供了类似C++手动管理内存的能力。
 * AtomicInteger实现的思想是：每个线程中都会拥有共享变量的一份私有拷贝，
 *      但由于多个线程都抢着操作共享变量，当前线程的私有拷贝已经不是共享变量的最新值；
 *      当前线程的私有拷贝只有和共享变量的最新值相等时，才能获得对共享变量的操作权利，这个操作是原子性的；
 *      当前线程的私有拷贝如何追上共享变量的最新值呢？就是在循环中不停的和共享变量最新值比，
 *      如果不相等，就把自己更新成最新值，再比，不等再更新，直到自己和最新值相等才获得了对共享变量的操作权；
 *      这套规则是AtomicInteger设计的，操作其维护的int值的线程遵守之；
 *         get()	直接返回值
 *         addAndGet(int)	增加指定的数据后返回增加后的数据，相当于 i++
 *                 getAndAdd(int)	增加指定的数据，返回变化前的数据，相当于 ++i
 *         getAndIncrement()	增加1，返回增加前的数据
 *         getAndDecrement()	减少1，返回减少前的数据
 *         getAndSet(int)	设置指定的数据，返回设置前的数据
 *         decrementAndGet()	减少1，返回减少后的值
 *         incrementAndGet()	增加1，返回增加后的值
 *         floatValue()	转化为浮点数返回
 *         intValue()	转化为int 类型返回
 *         set(int)	设置为给定值
 *         lazySet(int)	仅仅当get时才会set http://ifeve.com/juc-atomic-class-lazyset-que/
 *         compareAndSet(int, int)	尝试新增后对比，若增加成功则返回true否则返回false
 *
 */
public class Atomic {

    private volatile Integer i=0;

//    public AtomicInteger i=new AtomicInteger(0);
//
//    public int addI() {
//       return  i.incrementAndGet();//增加后的值,相当于++i
//    }
//    public int addI1() {
//        return  i.getAndIncrement();//增加前的值,相当于i++
//    }
//    public int getI() {
//        return i.get();
//    }

    public Integer getI() {
        return i;
    }

    public Integer addI() {
        return i++;
    }
    public static void main(String[] args) {
        Atomic atomic=new Atomic();
        //创建线程池
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(200);
        for (int i = 0; i < 100; i++) {
            scheduler.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    System.out.println("线程:" + Thread.currentThread().getName() + " count=" + atomic.addI());
                }
            });
        }
        scheduler.shutdown();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("最终结果是 ：" + atomic.getI());


    }

}
