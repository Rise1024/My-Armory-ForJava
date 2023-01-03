package com.base.jucAqs;/**
 * @author zds
 * @Description
 *
 * 允许多个线程相互等待，直到到达某个公共屏障点，（重点）只有当每个线程都准备就绪后，才能各自往下执行后面的操作；
 * 其和CountDownLatch相似的地方是都是通过计数器实现；
 * 当某个现场调用await()后，该线程就进入等待状态，而且计数器执行加1操作，当计数器达到了设置的初始值，调用await()方法进行等待的线程都会被唤醒，继续await()之后的操作；
 * 由于CyclicBarrier在释放等待线程后可以重用，所以称其为循环屏障；
 * 应用场景：
 * 多线程计算数据，最后合并计算结果；
 * 如果发生错误，可以重置计数器，让线程重新执行1次；
 * @createTime  2022/4/11 18:37
 */


import java.util.concurrent.*;

public class CyclicBarrierDemo {

    /**
     * CyclicBarrier屏障可以循环使用
     */
    private static CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        System.out.println("callback is running");
    });

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                }
            });
        }
        barrier.await(2000,TimeUnit.MILLISECONDS);
        System.out.println("主线程等不及了");
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        System.out.println("{} is ready"+threadNum);
        barrier.await();
        System.out.println("{} continue"+threadNum);
    }
}
