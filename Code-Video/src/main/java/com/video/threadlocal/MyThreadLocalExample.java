package com.video.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.*;

public class MyThreadLocalExample {

    private static InheritableThreadLocal<String> requestParam = new InheritableThreadLocal<>();
//    private static ThreadLocal<String> requestParam = new ThreadLocal<>();
    public static void setRequestParam(String value) {
        requestParam.set(value);
    }
    public static String getRequestParam() {
        return requestParam.get();
    }
    public static void main(String[] args) {
        // 设置请求参数值
        setRequestParam("example value");
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                // 在线程池中的线程中获取父线程的值
                System.out.println("子线程---"+Thread.currentThread().getName()+"获取变量:"+getRequestParam());
            });
        }
        executorService.shutdown();
        System.out.println("父线程---"+Thread.currentThread().getName()+"获取变量:"+getRequestParam());
    }
}
