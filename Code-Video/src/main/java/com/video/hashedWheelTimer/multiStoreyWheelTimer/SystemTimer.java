package com.video.hashedWheelTimer.multiStoreyWheelTimer;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

/**
 * @Auther: zds
 * @Date: 2022/11/09/15:10
 * @Description:多层时间轮
 */
public class SystemTimer implements KafkaTimer {

    private String executorName;
    private long tickMs;
    private int wheelSize;
    private long startMs;
    private Long timeoutMs;

    private ExecutorService taskExecutor;

    private ExecutorService bossThreadPool;

    private DelayQueue<TimerTaskList> delayQueue;
    private AtomicInteger taskCounter;
    private TimingWheel timingWheel;
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    public SystemTimer(String executorName, long tickMs, int wheelSize, Long timeoutMs) {
        this.executorName = executorName;
        this.tickMs = tickMs;
        this.wheelSize = wheelSize;
        this.startMs = TimeUtils.hiResClockMs();
        this.timeoutMs=timeoutMs;
        init();
    }

    private void init() {
        this.taskExecutor = Executors.newFixedThreadPool(1, r -> new Thread(r, "executor-" + executorName));
        this.delayQueue = new DelayQueue<>();
        this.taskCounter = new AtomicInteger(0);
        this.timingWheel = new TimingWheel(
                tickMs,
                wheelSize,
                startMs,
                taskCounter,
                delayQueue
        );
        bossThreadPool = Executors.newFixedThreadPool(1);
        //声明线程推动时间轮
        bossThreadPool.submit(() -> {
            while (true) {
                this.advanceClock(timeoutMs);
            }
        });

    }

    @Override
    public void add(TimerTask timerTask) {
        readLock.lock();
        try {
            addTimerTaskEntry(new TimerTaskEntry(timerTask, timerTask.delayMs + TimeUtils.hiResClockMs()));
        } finally {
            readLock.unlock();
        }
    }

    private Consumer<TimerTaskEntry> reinsert = this::addTimerTaskEntry;

    private void addTimerTaskEntry(TimerTaskEntry timerTaskEntry) {
        if (!timingWheel.add(timerTaskEntry)) {
            if (!timerTaskEntry.cancelled()) {
                taskExecutor.submit(timerTaskEntry.timerTask);
            }
        }
    }


    /*
     * 如果存储任务过期，则推动时钟。如果调用时没有任何过期的bucket，请等待timeoutMs后再放弃。
     */
    @Override
    public boolean advanceClock(Long timeoutMs) {
        TimerTaskList bucket = null;
        try {
            bucket = delayQueue.poll(timeoutMs, TimeUnit.MILLISECONDS);//从延迟队列获取槽
        } catch (InterruptedException e) {
            //
        }
        if (bucket != null) {
            writeLock.lock();
            try {
                while (bucket != null) {
                    timingWheel.advanceClock(bucket.getExpiration());
                    bucket.flush(reinsert);
                    bucket = delayQueue.poll();
                }
            } finally {
                writeLock.unlock();
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int size() {
        return taskCounter.get();
    }

    @Override
    public void shutdown() {
        taskExecutor.shutdown();
    }
}
