package com.video.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
/**
 * @Author Admin
 * @Date 2023/8/23 17:53
 * @Description TransmittableThreadLocal 测试类
 **/
public class TransmittableThreadLocalTest {
 
    // 创建一个TransmittableThreadLocal对象
//    private static TransmittableThreadLocal<Integer> threadLocal = new TransmittableThreadLocal<>();
    private static InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
 
        // 创建三个任务并提交给线程池
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Task(i));
        }
 
        // 关闭线程池
        executorService.shutdown();
    }
 
    // 自定义任务类
    static class Task implements Runnable {
        private int taskId;
 
        public Task(int taskId) {
            this.taskId = taskId;
        }
 
        @Override
        public void run() {
            // 生成一个随机数

            Random random = new Random();
            int randomNumber = random.nextInt(); // 生成一个随机整数
 
            // 将随机数存储到TransmittableThreadLocal中
            threadLocal.set(randomNumber);
 
            // 打印当前线程ID和随机数
            System.out.println("线程ID: " + Thread.currentThread().getId() + ", 任务ID: " + taskId + ", 随机数值: " + threadLocal.get());
 
            // 模拟一些计算操作
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("<===============计算完成===============>");
 
            // 打印当前线程ID和随机数
            System.out.println("线程ID: " + Thread.currentThread().getId() + ", 任务ID: " + taskId + ", 随机数值: " + threadLocal.get());
        }
    }
}