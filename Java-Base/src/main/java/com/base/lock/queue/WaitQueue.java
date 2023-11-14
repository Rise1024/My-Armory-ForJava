package com.base.lock.queue;

import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class WaitQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<>(1);
        System.out.println(strings.offer("1"));

        System.out.println(strings.offer("2"));
        System.out.println(strings.offer("3"));

    }
}
