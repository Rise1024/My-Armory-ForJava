package com.base.threadSafety;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zds
 * @Description 原子类
 * @createTime 2022/3/24 17:28
 */
public class Atomic {


    @Data
    @AllArgsConstructor
    public static class  Person{
        private String name;
        private Integer birthday;

        public int compareByAge(Person a, Person b) {
            return a.getBirthday().compareTo(b.getBirthday());
        }
    }

//        基本类型：AtomicBoolean，AtomicInteger，AtomicLong
//        数组类型：tomicIntegerArray，AtomicLongArray，AtomicReferenceArray
//        引用类型：AtomicReference，AtomicMarkableReference，AtomicStampedReference
//        对象的属性修改类型 ：AtomicIntegerFieldUpdater，AtomicLongFieldUpdater，AtomicReferenceFieldUpdater
//        JDK1.8 新增：DoubleAccumulator、LongAccumulator、DoubleAdder、LongAdder、Striped64

    /**
     AtomicInteger实现的思想是：每个线程中都会拥有共享变量的一份私有拷贝，
     但由于多个线程都抢着操作共享变量，当前线程的私有拷贝已经不是共享变量的最新值；
     当前线程的私有拷贝只有和共享变量的最新值相等时，才能获得对共享变量的操作权利，这个操作是原子性的；
     当前线程的私有拷贝如何追上共享变量的最新值呢？就是在循环中不停的和共享变量最新值比，
     如果不相等，就把自己更新成最新值，再比，不等再更新，直到自己和最新值相等才获得了对共享变量的操作权；
     这套规则是AtomicInteger设计的，操作其维护的int值的线程遵守之；
     *
     * 其他的原子类原理一样

     */
    //        基本类型：AtomicBoolean，AtomicInteger，AtomicLong
//        数组类型：tomicIntegerArray，AtomicLongArray，AtomicReferenceArray
//        引用类型：AtomicReference，AtomicMarkableReference，AtomicStampedReference
//        对象的属性修改类型 ：AtomicIntegerFieldUpdater，AtomicLongFieldUpdater，AtomicReferenceFieldUpdater
//        JDK1.8 新增：DoubleAccumulator、LongAccumulator、DoubleAdder、LongAdder、Striped64

//        get()	直接返回值
//        addAndGet(int)	增加指定的数据后返回增加后的数据，相当于 i++
//                getAndAdd(int)	增加指定的数据，返回变化前的数据，相当于 ++i
//        getAndIncrement()	增加1，返回增加前的数据
//        getAndDecrement()	减少1，返回减少前的数据
//        getAndSet(int)	设置指定的数据，返回设置前的数据
//        decrementAndGet()	减少1，返回减少后的值
//        incrementAndGet()	增加1，返回增加后的值
//        floatValue()	转化为浮点数返回
//        intValue()	转化为int 类型返回
//        set(int)	设置为给定值
//        lazySet(int)	仅仅当get时才会set http://ifeve.com/juc-atomic-class-lazyset-que/
//        compareAndSet(int, int)	尝试新增后对比，若增加成功则返回true否则返回false

    public static void main(String[] args) {
        //CSA
        AtomicInteger atomicInteger=new AtomicInteger(6);
        System.out.println(atomicInteger.compareAndSet(6,3));
        System.out.println(atomicInteger.get());

        //原子类引用类型
        Atomic.Person tom = new Atomic.Person("tom",18);
        Atomic.Person jim = new Atomic.Person("jim",20);

        AtomicReference<Atomic.Person> user = new AtomicReference<>();
        user.set(tom);

        System.out.println(user.compareAndSet(tom, jim)+"\t"+user.get().toString());
        System.out.println(user.compareAndSet(tom, jim)+"\t"+user.get().toString());
        //AtomicStampedReference<V> 解决ABA问题 增加版本号
    }

}
