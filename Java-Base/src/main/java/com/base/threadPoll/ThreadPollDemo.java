package com.base.threadPoll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author zds
 * @Description
 * @createTime 2022/4/21 15:23
 */
public class ThreadPollDemo {
    //线程池线程数设置
//    CPU 密集型的程序 - 核心数 + 1
//    I/O 密集型的程序 - 核心数 * 2

    /**Nthreads=Ncpu*Ucpu *(1+W/C)
     *
     * 在理想情况下线程数=cpu核心数*期待cpu利用率*（1+线程等待时间/计算时间）
     *
     * */

    //固定数量线程池
    private static final ExecutorService executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1,new NamedThreadFactory("test_fixed"));

    //单线程线程池
    private static final ExecutorService executorService1= Executors.newSingleThreadExecutor(new NamedThreadFactory("test_single"));

   //定时线程池
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()+1,new NamedThreadFactory("test_fixed"));



    public static class work implements Runnable {
        private int a;

        public work(int a) {
            this.a = a;
        }

        @Override
        public void run() {
//            System.out.println(Thread.currentThread().getName()+"----"+a);

            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName()+"----"+i);
            }


        }
    }

    public static class work1  {
        private int a;

        public work1(int a) {
            this.a = a;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName()+"----"+a);
        }
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 4; i++) {
            executorService.submit(new work(i));

        }
    }
}
