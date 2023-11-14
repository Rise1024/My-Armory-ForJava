package com.virtual_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Executors.newVirtualThreadPerTaskExecutor创建

/**
 * 具有虚拟线程的 ExecutorService
 * 从 java 5 开始，鼓励您使用 ExecutorServices 而不是直接使用 Thread 类。
 * 在 Java 21 中，我们获得了一个使用虚拟线程的新 ExecutorService
 */
public class ExecutorsnewVirtualThreadPerTaskExecutorTest {
    public static void main(String[] args) {
        CustomThread4 customThread = new CustomThread4();
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        executor.submit(customThread);
    }
}

class CustomThread4 implements Runnable {
    @Override
    public void run() {
        System.out.println("CustomThread run");
    }
}
