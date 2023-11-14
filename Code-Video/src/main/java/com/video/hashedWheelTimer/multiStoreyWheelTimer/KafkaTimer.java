package com.video.hashedWheelTimer.multiStoreyWheelTimer;

/**
 * @Auther: zds
 * @Date: 2022/11/09/15:10
 * @Description:
 */
public interface KafkaTimer {
    /**
     * 添加新任务
     * @param timerTask
     */
    void add(TimerTask timerTask);

    /**
     *
     *推进时间轮,执行过期任务
     * @param timeoutMs
     * @return 是否执行了任务
     */
    boolean advanceClock(Long timeoutMs);

    /**
     * 获取等待执行的任务数
     * @return the number of tasks
     */
    int size();

    /**
     * 关闭计时器服务
     */
    void shutdown();
}
