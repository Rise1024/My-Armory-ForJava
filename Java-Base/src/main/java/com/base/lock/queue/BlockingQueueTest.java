package com.base.lock.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zds
 * @Date: 2023/03/29/16:33
 * @Description:
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(1024);
        boolean add = queue.add(2);

    }
}
