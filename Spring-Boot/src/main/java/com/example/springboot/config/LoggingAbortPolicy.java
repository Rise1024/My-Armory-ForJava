package com.example.springboot.config;

import com.codahale.metrics.Histogram;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by zds on 5/31/16.
 */
@Slf4j
public class LoggingAbortPolicy extends ThreadPoolExecutor.AbortPolicy {
    private final Histogram histogram;

    public LoggingAbortPolicy(Histogram histogram) {
        super();
        this.histogram = histogram;
    }
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        histogram.update(1);
        log.warn("threadk task {} is rejected by thread pool, current pool size is {}", r, e.getActiveCount());
        super.rejectedExecution(r, e);
    }
}
