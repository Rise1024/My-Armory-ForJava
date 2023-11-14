package com.base.lock.jucAqs;

/**
 * @author zds
 * @Description
 * 信号量，可以控制并发访问的线程个数；
 * 可以很容易的控制某个资源被同时访问的个数；
 * Semaphore使用场景
 * 用于仅能提供优先访问的资源，比如数据库的连接，最大只有20；
 * @createTime 2022/4/11 18:14
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    private final static int threadCount = 20;
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);//允许3个并发
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) { // 尝试获取一个许可
                        System.out.println( "线程"+threadNum );
                        Thread.sleep(1000);
                        semaphore.release(); // 释放一个许可
                    }
                } catch (Exception e) {
                }
            });
        }
        exec.shutdown();
    }
}