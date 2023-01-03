package com.base.jucAqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zds
 * @Description
 * 并发容器 AQS是java.util.concurrent.locks.AbstractQueuedSynchronizer的简写，是J.U.C包中最核心的类；
 *提供了一个队列（FIFO），这个队列可以作为构建锁或者其他同步装置的基础，争用资源的线程被阻塞后，就会插入该队列；
 * 底层是双向链表Sync Queue，head节点主要用于后续调度；
 * Condition Queue不是必须的，只有程序中需要使用condition的时候才会需要这个链表，Condition Queue可能有多个；
 * 利用int类型表示状态，在AQS中有一个叫state的成员变量，这个state其实就是代表了共享的资源，在不同实现的子类中，其含义不同；
 * 基于AQS有一个同步组件ReentrantLock，在ReentrantLock中，state表示获取锁的线程数，比如state=0，表示还没有线程获取锁；
 * 使用方法是继承，其设计基于模板方法模式，子类通过继承并实现其方法管理状态（acquire, release）；
 * AQS可以同时实现排它锁和共享锁模式，但使用时，同时只能使用一种模式；
 *
 * 实现AQS的同步组件
 * CountDownLatch：是一个闭锁，通过计数，来保证线程是否需要一直被阻塞；
 * Semaphore：能控制同一时间，并发线程的数目；
 * CyclicBarrier：和CountDownLatch很像，都能阻塞进程；
 * ReenTrantLock：可重入锁；
 * Condition：其在使用时，需要ReenTrantLock；
 * FutureTask：可以拿到线程的返回值；
 *
 * 提供的API
 * isHeldExclusively：该线程是否独占资源；
 * tryAcquire / tryRelease：独占的方式尝试获取或释放资源；
 * tryAcquireShared / tryReleaseShared：共享的方式尝试获取或释放资源；

 *
 * @createTime 2022/4/11 16:21
 *
 * 代码重构
 */
public class CountDownLatchDemo {


    /**
     * 阻塞当前线程功能；
     * 初始化的时候指定了计数器的值，多线程对计数器的值的操作是原子性的，同时只能有一个线程操作其中计数器的值；
     * 调用该类的await()方法的线程会一直处于阻塞状态，直到其他线程调用countDown()，将当前计数器的值减为0，每次调用countDown()的时候，计数器的值减1，当计数器的值减为0时，所有因调用await()方法而阻塞的线程都会继续执行；
     * 其中计数器的值是不能被重置的，如果是需要重置计数器的版本，可以考虑CyclicBarrier；
     **/
    private synchronized static void test(int threadNum,CountDownLatch countDownLatch) throws Exception {
        Thread.sleep(1000);
        System.out.println( "线程"+threadNum );
        System.out.println( countDownLatch.getCount() );
    }

        private final static int threadCount = 10;

        public static void main(String[] args) throws Exception {

            ExecutorService exec = Executors.newCachedThreadPool();

            final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

            for (int i = 0; i < threadCount; i++) {
                final int threadNum = i;
                exec.execute(() -> {
                    try {
                        test(threadNum,countDownLatch);
                    } catch (Exception e) {
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }
            countDownLatch.await(1, TimeUnit.SECONDS);//如果await添加等待时间，将不会考虑countDownLatch是否为0
            System.out.println("finish");
            exec.shutdown();
        }


}