package com.video.hashedWheelTimer.multiStoreyWheelTimer;

/**
 * @Auther: zds
 * @Date: 2022/11/09/15:10
 * @Description:
 */
public abstract class TimerTask implements Runnable {

    protected long delayMs;

    public TimerTask(long delayMs) {
        this.delayMs =  delayMs;
    }

    protected TimerTaskEntry timerTaskEntry;

    public synchronized void cancel() {
        if (timerTaskEntry != null) {
            timerTaskEntry.remove();
        }
        timerTaskEntry = null;
    }

    public synchronized void setTimerTaskEntry(TimerTaskEntry entry) {
        if (timerTaskEntry != null && timerTaskEntry != entry) {
            timerTaskEntry.remove();
        }
        timerTaskEntry = entry;
    }

    public TimerTaskEntry getTimerTaskEntry() {
        return timerTaskEntry;
    }

}
