package com.video.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

public class TransmittableThreadLocalExample {
    public static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("Hello");

        Runnable task = () -> {
            System.out.println("Thread: " + threadLocal.get()); // 输出：Thread: Hello
        };

        // 使用TtlRunnable包装任务，确保在线程池中正确传递线程局部变量的值
        Runnable ttlTask = TtlRunnable.get(task);

        Thread thread = new Thread(ttlTask);
        thread.start();
    }
}
