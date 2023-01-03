package com.base.jucAqs;

/**
 * @author zds
 * @Description
 *
 * Java中的锁大体分2类；
 * 1、synchronized修饰的锁；
 * 2、J.U.C中提供的锁，其中核心的就是ReentrantLock；
 *
 * synchronized锁和ReentrantLock的区别：

 * synchronized是非公平锁、重入锁（同一个线程可以反复获取锁多次，然后需要释放多次），由jvm提供
 *
 * ReentrantLock是一个可重入的同步锁，可以通过设置参数决定使用公平锁和非公平锁，由JDK提供
 * 可以通过参数决定公平和非公平
 *
 * 公平锁：
 * 是按照锁申请的顺序来获取锁，线程直接进入同步队列中排队，队列中的第一个线程才能获得到锁。
 * 非公平锁:
 * 非公平锁是线程申请锁时，直接尝试加锁，获取不到才会进入到同步队列排队。
 * 如果此时该线程刚好获取到了锁，那么它不需要因为队列中有其他线程在排队而阻塞，省去了CPU唤醒该线程的开销。
 * 而对于已经在同步队列中的线程，仍然是按照先进先出的公平规则获取锁
 *
 * 便利性：synchronized的使用更方便简洁，通过编译器实现加锁和释放；
 * ReentrantLock需要手工声明来加锁和释放锁，最好在finally中释放锁，以避免死锁；
 * 锁的细粒度 & 灵活度：ReentrantLock优于synchronized
 *
 *
 * 锁的选择：
 * 当只有少量竞争者的时候，synchronized是一个很好的通用的锁实现，而且synchronized不会引发死锁，JVM会自动解锁；
 * 竞争者不少，但线程的增长是可以预估的，ReentrantLock是一个很好的通用的锁实现；
 * 注意：选择锁的时候并不是最高级的才是最好的，适合自己使用场景的才是最好的
 *

 *

 * @createTime 2022/4/11 19:03
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    // 请求总数
    public static int clientTotal = 50;

    // 同时并发执行的线程数
    public static int threadTotal = 5;

    public static int count = 0;

    private final static Lock lock = new ReentrantLock(); //非公平锁
    private final static Lock lock1 = new ReentrantLock(true);//公平锁

    /**
     * 通过信号量和计时器并对加法加锁实现了线程安全的数量求和
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);//信号量 允许并发的数量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); //计数器，调用await的线程会一直等待阻塞，直到clientTotal减为0
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:"+count);
    }

    private static void add() {
        lock.lock();
        try {
            try {
                count++;
                Thread.sleep(1000);
                System.out.println("这个线程花费了一些时间得到"+count);
            } catch (InterruptedException e) {
            }

        } finally {
            lock.unlock();
        }
    }
}
