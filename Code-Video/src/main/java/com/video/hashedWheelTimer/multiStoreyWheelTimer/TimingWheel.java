package com.video.hashedWheelTimer.multiStoreyWheelTimer;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: zds
 * @Date: 2022/11/09/15:10
 * @Description:时间轮
 */
public class TimingWheel {

    /**
     * 格子大小
     */
    private long tickMs;
    /**
     * 轮子大小
     */
    private int wheelSize;

    /**
     * 开始计算时间
     */
    private long startMs;
    private AtomicInteger taskCounter;
    private DelayQueue<TimerTaskList> queue;

    private long interval;
    private TimerTaskList[] buckets;
    private long currentTime;

    private TimingWheel overflowWheel;

    public TimingWheel(long tickMs, int wheelSize, long startMs, AtomicInteger taskCounter, DelayQueue<TimerTaskList> queue) {
        this.tickMs = tickMs;
        this.wheelSize = wheelSize;
        this.startMs = startMs;
        this.taskCounter = taskCounter;
        this.queue = queue;
        init();
    }

    private void init() {
        this.interval = tickMs * wheelSize;
        this.buckets = new TimerTaskList[wheelSize];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new TimerTaskList(taskCounter);
        }
        this.currentTime = startMs - (startMs % tickMs);
        this.overflowWheel = null;
    }

    private void addOverflowWheel() {
        synchronized (this) {
            if (overflowWheel == null) {
                overflowWheel = new TimingWheel(
                        interval,
                        wheelSize,
                        currentTime,
                        taskCounter,
                        queue
                );
            }
        }
    }

    public boolean add(TimerTaskEntry timerTaskEntry) {
        long expiration = timerTaskEntry.expirationMs;

        if (timerTaskEntry.cancelled()) {
            return false;
        } else if (expiration < currentTime + tickMs) {   //已经到期
            return false;
        } else if (expiration < currentTime + interval) { //在本层范围内
            long virtualId = expiration / tickMs;
            TimerTaskList bucket = buckets[(int) (virtualId % wheelSize)];//计算槽位
            bucket.add(timerTaskEntry); //添加到槽的双向链表中
            if (bucket.setExpiration(virtualId * tickMs)) {
                queue.offer(bucket); //把槽添加到延迟队列中，排序，避免空推动时间轮
            }
            return true;
        } else {
            if (overflowWheel == null) {
                addOverflowWheel();//创建上层时间轮，格子大小为本层的数组大小，轮子格数一样
            }
            return overflowWheel.add(timerTaskEntry);
        }
    }

    public void advanceClock(Long timeMs) {
        if (timeMs >= currentTime + tickMs) {
            currentTime = timeMs - (timeMs % tickMs);
            if (overflowWheel != null) {
                overflowWheel.advanceClock(currentTime);
            }
        }
    }

}
