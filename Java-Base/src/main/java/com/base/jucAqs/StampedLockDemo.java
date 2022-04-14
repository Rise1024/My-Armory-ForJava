package com.base.jucAqs;

/**
 * @author zds
 * @Description
 *
 * StampedLock控制锁有3种模式：写，读，乐观读；
 * 其状态由版本和模式2个部分组成；
 * 锁获取方法返回的是一个数字作为票据， 其用相关的锁状态表示并控制相关的访问，数字0表示没有写锁被授权访问；
 * 在读锁上分悲观锁和乐观锁，乐观读指的是读的操作很多，写的操作很少，我们可以乐观的认为写入和读取同时发生的几率很小
 * 因此不悲观的使用完全的读取锁定，程序可以读取资料之后查看是否遭到写入操作的变更，再采取后续的措施，这个改进可以大幅提升程序吞吐量；
 *

 * @createTime 2022/4/12 11:17
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    // 请求总数
    public static int clientTotal = 50;

    // 同时并发执行的线程数
    public static int threadTotal = 5;

    public static int count = 0;

    private final static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);//信号量，控制并发
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//计数器，控制求和的主线程的执行时机
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("exception"+e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:"+count);
    }

    private static void add() {
        long stamp = lock.writeLock();
        System.out.println(stamp);
        try {
            count++;
        } finally {
            lock.unlock(stamp);
        }
    }
}
