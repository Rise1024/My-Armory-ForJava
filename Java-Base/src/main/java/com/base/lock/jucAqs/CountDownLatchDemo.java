package com.base.lock.jucAqs;

import lombok.Synchronized;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zds
 * @Description 计数器 AQS 共享锁
 * CountDownLatch：是一个闭锁，通过计数，来保证线程是否需要一直被阻塞；
 * Semaphore：能控制同一时间，并发线程的数目；
 * CyclicBarrier：和CountDownLatch很像，都能阻塞进程；
 * 提供的API
 * isHeldExclusively：该线程是否独占资源；
 * tryAcquire / tryRelease：独占的方式尝试获取或释放资源；
 * tryAcquireShared / tryReleaseShared：共享的方式尝试获取或释放资源；
 * @createTime 2022/4/11 16:21
 */
public class CountDownLatchDemo {
    /**
     * 阻塞当前线程功能；
     * 初始化的时候指定了计数器的值，多线程对计数器的值的操作是原子性的，同时只能有一个线程操作其中计数器的值；
     * 调用该类的await()方法的线程会一直处于阻塞状态，直到其他线程调用countDown()，将当前计数器的值减为0，每次调用countDown()的时候，计数器的值减1，当计数器的值减为0时，所有因调用await()方法而阻塞的线程都会继续执行；
     * 其中计数器的值是不能被重置的，如果是需要重置计数器的版本，可以考虑CyclicBarrier；
     **/
        private final static int threadCount = 3;
        public static void main(String[] args) throws Exception {
            final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
            ExecutorService exec = Executors.newCachedThreadPool();
            for (int i = 0; i < threadCount; i++) {
                final int threadNum = i;
                exec.execute(() -> {
                    try {
                            countDownLatch.countDown();
                            System.out.println( "线程"+threadNum );
                            System.out.println( countDownLatch.getCount() );
                    } catch (Exception e) {
                    }
                });
            }
            countDownLatch.await(1, TimeUnit.SECONDS);//如果await添加等待时间，将不会考虑countDownLatch是否为0
            System.out.println("finish");
            exec.shutdown();
        }


}