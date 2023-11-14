package com.virtual_thread;

//使用Thread.ofVirtual()创建

public class ThreadofVirtualTest {
    public static void main(String[] args) {
        CustomThread1 customThread = new CustomThread1();
        // 创建不启动
//        Thread unStarted = Thread.ofVirtual().unstarted(customThread);
//        unStarted.start();
        // 创建直接启动
        Thread.ofVirtual().start(customThread);
    }
}

 class CustomThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println("CustomThread run");
    }
}
