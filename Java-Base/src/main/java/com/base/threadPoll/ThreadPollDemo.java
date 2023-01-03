package com.base.threadPoll;

import java.util.concurrent.*;

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
    private static final ExecutorService executorService= Executors.newFixedThreadPool(10,new NamedThreadFactory("test_fixed"));

    //单线程线程池
    private static final ExecutorService executorService1= Executors.newSingleThreadExecutor(new NamedThreadFactory("test_single"));

    //定时线程池
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()+1,new NamedThreadFactory("test_fixed"));


    public static class work implements Runnable {

       private final ArrayBlockingQueue<String> queue;
       private final int a;


        public work(ArrayBlockingQueue<String> queue,int a) {
            this.queue = queue;
            this.a=a;
        }

        @Override
        public void run() {
            int count=0;
//            System.out.println(Thread.currentThread().getName()+"----");
//            System.out.println(a+"----");
            while (true){
                String poll = queue.poll();
                try {
                    if (++count==1){
                        poll=poll+"flag";
                        Thread.sleep(5000);
                    }
                    System.out.println("count---"+count);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("poll---"+poll);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }


    public static class work2 implements Runnable {

        private final ArrayBlockingQueue<String> queue;
        private int a;

        public work2(ArrayBlockingQueue<String> queue,int a) {
            this.queue = queue;
            this.a=a;
        }

        @Override
        public void run() {
            while (true){
                try {
                    queue.offer(String.valueOf(a),10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
//            System.out.println(Thread.currentThread().getName()+"----");
//            System.out.println(a+"----");
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

//         final ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<>(20);
//        for (int i = 0; i < 19; i++) {
//            try {
//                System.out.println("offer----"+i);
//                queue.offer("--------"+i,10,TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
////        try {
////            Thread.sleep(1000);
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        }
//
//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new work(queue,i));
//        }

        int a=0;

        for (int i = 0; i < 2; i++) {
            if (++a%10==0){
                System.out.println("flag----"+a);
            }
        }
        if (a%10!=0){
            System.out.println(a);
        }


    }
}
