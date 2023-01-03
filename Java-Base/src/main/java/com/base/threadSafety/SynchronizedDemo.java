package com.base.threadSafety;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author zds
 * @Description
 * 线程安全三要素：
 * 可见性：假设共享对象最初存储在主内存中。在 CPU 1上运行的线程将共享对象读入它的CPU缓存后修改，但是还没来得及即刷新回主内存，这时其他 CPU 上运行的线程就不会看到共享对象的更改。这样，每个线程都可能以自己的线程结束，就出现了可见性问题
 * 原子性：如果线程 A 将一个共享对象的变量读入到它的 CPU 缓存中。此时，线程 B 执行相同的操作，但是进入不同的 CPU 缓存。现在线程A执行 +1 操作，线程B也这样做。现在该变量增加了两次，在每个 CPU 缓存中一次。如果这些增量是按顺序执行的，则变量结果会是 3，并将原始值 +2 写回主内存。但是，这两个增量是同时执行的，没有适当的同步
 * 顺序性：多线程环境中线程交替执行，由于编译器优化重排的存在，两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测
 *  1、Volatile线程共享
 *  2、Synchronized 关键字
 *  *   1、修饰代码块：被修饰的代码称作同步语句块，其作用范围是大括号括起来的代码，作用于调用的对象（可以自己指定锁定对象）；
 *
 *  * 2、 修饰方法：被修饰的方法称为同步方法，作用范围是整个方法，作用于调用的对象（this）；
 *        同步方法synchronized关键字不能继承。
 *         虽然可以使用synchronized来定义方法，但synchronized并不属于方法定义的一部分，因此，synchronized关键字不能被继承。
 *         如果在父类中的某个方法使用了synchronized关键字，而在子类中覆盖了这个方法，在子类中的这个方法默认情况下并不是同步的，而必须显式地在子类的这个方法中加上synchronized关键字才可以。
 *         当然，还可以在子类方法中调用父类中相应的方法，这样虽然子类中的方法不是同步的，但子类调用了父类的同步方法，因此，子类的方法也就相当于同步了
 *         在子类方法中加上synchronized关键字
 *
 *  * 3、 修饰静态方法：作用范围是整个静态方法，作用于这个类的所有对象，本质是对 Class 对象上锁；
 *         无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象；
 *         如果synchronized作用的对象是一个静态方法或一个类，则它取得的锁是对类，该类所有的对象同一把锁。
 *  * 4、 修饰类：作用范围是synchronized后面的括号括起来的部分，作用于这个类的所有对象，本质是对 Class 对象上锁；
 *
 *  * 情况一：两个线程同时访问一个对象的 synchronized 方法，同步执行；
 *  * 情况二：两个线程访问的是两个对象的 synchronized 方法，并行执行；
 *  * 情况三：两个线程同时访问一个 synchronized 静态方法，同步执行；
 *  * 情况四：两个线程同时访问 synchronized 方法与非 synchronized 方法，并发执行；
 *  * 情况五：两个线程同时访问同一个对象的不同 synchronized 方法，同步执行；
 *  * 情况六：两个线程同时访问静态 synchronized 方法和非静态 synchronized 方法，并行执行；
 *  * 情况七：方法抛异常后，会释放锁；
 *  * @createTime 2022/4/12 11:36
 *
 * @createTime 2022/3/24 17:27
 */

/**
 * volatile 是 Java 虚拟机提供的轻量级的同步机制
 * 线程共享,保证可见性
 * 不保证原子性
 * 禁止指令重排（单例）
 */
/**
 * * Synchronized 关键字
 * 线程共享,保证可见性
 * 保证原子性
 * 禁止指令重排 有序性
 */


public class SynchronizedDemo {

    //修饰代码块，作用于调用对象
    public void test1(int j) {
        synchronized (this) {
            System.out.println(this);
            for (int i = 0; i < 10; i++) {
                System.out.println("synchronized block object "+j+"-"+i);
            }
        }
    }
    //修饰代码块，作用于类
    public void test2(int j) {
        synchronized (SynchronizedDemo.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println("synchronized block object "+j+"-"+i);
            }
        }
    }
    //不同对象在不同线程中访问同步代码块，同步代码块对不同调用对象是不能起到同步作用的；
    private static void testCodeBlockNotSync() {


    }


    public static void main(String[] args) {
        SynchronizedDemo example1 = new SynchronizedDemo();
        SynchronizedDemo example2 = new SynchronizedDemo();
        new Thread(()->{
            example1.test1(1);
        }).start();
        new Thread(()->{
            example2.test1(2);
        }).start();
    }


}
