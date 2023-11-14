package com.base.lock.jucAqs;

import java.util.concurrent.locks.LockSupport;

/**
 * @Auther: zds
 * @Date: 2023/03/07/13:52
 * @Description:
 * LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程
 * 即使先执行unpark，后park，依然可以被唤醒
 * 区别与wait和notify
 */
public class LockSupportDemo {
   static class MyThread extends Thread {
        private Object object;

        public MyThread(Object object) {
            this.object = object;
        }

        public void run() {
            System.out.println("before unpark");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            // 获取blocker
            System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));
            // 释放许可
            LockSupport.unpark((Thread) object);
            // 休眠500ms，保证先执行park中的setBlocker(t, null);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 再次获取blocker
            System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));

            System.out.println("after unpark");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("before park");
        // 获取许可
        LockSupport.park("ParkAndUnparkDemo");
        System.out.println("after park");
    }
}
