package com.performance;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


// 虚拟线程
public class VirtualThreadTest {
  static final AtomicInteger atomicInteger = new AtomicInteger();

  static Runnable runnable = () -> {
    try {
      Thread.sleep(Duration.ofSeconds(1));
    } catch(Exception e) {
      System.out.println(e);
    }
    System.out.println("Work Done - " + atomicInteger.incrementAndGet());
  };
  public static void main(String[] args) {
    Instant start = Instant.now();
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      for(int i = 0; i < 10000; i++) {
        executor.submit(runnable);
      }
    }
    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println("总耗时 : " + timeElapsed);
  }
}