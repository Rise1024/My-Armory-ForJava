package com.virtual_thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 您还可以创建一个工厂来为您创建虚拟线程。在以下示例中，创建了一个虚拟线程工厂。
 */
public class ThreadofVirtualfactoryTest {

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            group = Thread.currentThread().getThreadGroup();
            namePrefix = "test-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
    public static void main(String[] args) {
        CustomThread3 customThread = new CustomThread3();
        ThreadFactory factory = Thread.ofVirtual().factory();
        Thread thread = factory.newThread(customThread);
        thread.start();
    }
}

class CustomThread3 implements Runnable {
    @Override
    public void run() {
        System.out.println("CustomThread run");
    }
}
