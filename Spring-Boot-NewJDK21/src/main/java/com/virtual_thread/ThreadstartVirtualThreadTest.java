package com.virtual_thread;
//Thread.startVirtualThread创建
public class ThreadstartVirtualThreadTest {
    public static void main(String[] args) {
        CustomThread customThread = new CustomThread();
        Thread.startVirtualThread(customThread);
    }
}
class CustomThread implements Runnable {
    @Override
    public void run() {
        System.out.println("CustomThread run");
    }
}
