package com.base.lock.threadSafety;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *解决CAS ABA问题 AtomicStampedReference
 */
public class AtomicReference {

//    private static AtomicStampedReference<Integer> atomicStampedRef =
//            new AtomicStampedReference<>(1, 0);

    private static AtomicInteger atomicInteger =new AtomicInteger(1);

    public static void main(String[] args){
        first1().start();
        second1().start();

//        first().start();
//        second().start();
    }

    private static Thread first1() {
        return new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() +",初始值 a = " + atomicInteger.get());
            int currentValue = atomicInteger.get();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = atomicInteger.compareAndSet(currentValue,currentValue+1);
            System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess+" result:"+atomicInteger.get());
        },"主操作线程");
    }

    private static Thread second1() {
        return new Thread(() -> {
            Thread.yield();
            atomicInteger.compareAndSet(1,2);
            System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicInteger.get());
            atomicInteger.compareAndSet(2,1);
            System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicInteger.get());
        },"干扰线程");
    }



//    private static Thread first() {
//        return new Thread(() -> {
//            System.out.println("操作线程" + Thread.currentThread() +",初始值 a = " + atomicStampedRef.getReference());
//            int stamp = atomicStampedRef.getStamp(); //获取当前标识别
//            Integer reference = atomicStampedRef.getReference();
//            try {
//                Thread.sleep(1000); //等待1秒 ，以便让干扰线程执行
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            boolean isCASSuccess = atomicStampedRef.compareAndSet(reference,reference+1,stamp,stamp+1);  //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
//            System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess+"result:"+atomicStampedRef.getReference());
//        },"主操作线程");
//    }
//
//    /**
//     * 进行干扰
//     * @return
//     */
//    private static Thread second() {
//        return new Thread(() -> {
//            Thread.yield(); // 确保thread-first 优先执行
//            atomicStampedRef.compareAndSet(1,2,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
//            System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicStampedRef.getReference());
//            atomicStampedRef.compareAndSet(2,1,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
//            System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicStampedRef.getReference());
//        },"干扰线程");
//    }
}