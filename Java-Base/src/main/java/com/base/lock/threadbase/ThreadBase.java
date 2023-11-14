package com.base.lock.threadbase;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: zds
 * @Date: 2023/02/13/21:49
 * @Description:
 */
public class ThreadBase {
    /**
     * 继承Thread
     */
        class ExtendsThread extends Thread {
            @Override
            public void run() {
                System.out.println("ExtendsThread");
            }
        }
    /**
     * 实现Runnable
     */
        class RunnableThread implements Runnable {
            @Override
            public void run() {
                System.out.println("RunnableThread");
            }
        }

    /**
     *实现Callable接口
     */
    class CallableThread implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "CallableThread";
        }
    }

    public void test1() {
        //1
        Thread thread1 = new ExtendsThread();
        thread1.start();
        /**
         * 作为Thread的target来创建Thread对象，启动
         */
        Thread thread2 = new Thread(new RunnableThread());
        thread2.start();
        //3
        CallableThread callableThread=new CallableThread();
        FutureTask<String> futureTask = new FutureTask<>(callableThread);
        new Thread(futureTask).start();


        try {
            /**
             * Thread.yield()：一定是当前线程调用此方法，当前线程放弃获取CPU的时间片，由运行态转变为就绪态，让操作系统中再次选择线程执行。
             * 作用：让相同优先级的线程轮流执行，但并不能保证轮流执行，根据解释我们了解到，转成就绪态的的线程还有可能再次选中执行。Thread.yield()方法不会导致阻塞。
             *
             * t.join()/t.join(long millis)：当前线程调用t2.join()方法，当前线程阻塞但是不会释放对象锁，直到t2线程执行完毕或者millis时间到，则当前的线程恢复就绪状态。
             * 作用：让优先级比较高的线程优先执行。
             */
            thread1.join();
            Thread.yield();

            /**
             *
             */
            Thread.sleep(1);

            /**
             * obj.wait()/obj.wait(long timeout)是Object中的方法，当线程调用wait()方法，当前线程释放对象锁，进入等待队列。
             * obj.notify()/obj.nogifyAll()是Object中的方法，唤醒在此对象上wait()的单个或者所有线程。
             */
            wait();
            notify();
            notifyAll();


            LockSupport.park("sd");//
            LockSupport.unpark(new Thread("sd"));//

            /**
             * condition.await()/condition.await(long time, TimeUnit unit)：通过condition调用，当前线程释放对象锁。
             * condition.signal()/condition.signalAll()：通过signal或者signalAll方法唤醒await挂起的线程。
             */
            Condition condition = new ReentrantLock().newCondition();
            condition.await();//
            condition.signal();
            condition.signalAll();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



}
