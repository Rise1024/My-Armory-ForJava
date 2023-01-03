package com.example.springboot.config;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.ErrorHandler;


@Configuration
@Slf4j
public class TaskConfiguration {
    public static final String SCHEDULER_POOL_NAME = "kf-webapp-scheduler-pool-";
    @Value("${scheduler.task.executor.poolSize:2}")
    private int schedulerTaskExecutorPoolSize;
    @Autowired
    private MetricRegistry metricRegistry;

    @Bean
    public MetricRegistry getMetricsRegistry(){
        return new MetricRegistry();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        final Histogram histogram = metricRegistry.histogram(SCHEDULER_POOL_NAME+"size");
        final Histogram rejectedHistogram = metricRegistry.histogram(SCHEDULER_POOL_NAME+"rejected-size");
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix(SCHEDULER_POOL_NAME);
        taskScheduler.setRejectedExecutionHandler(new LoggingAbortPolicy(rejectedHistogram));
        taskScheduler.setPoolSize(schedulerTaskExecutorPoolSize);
        taskScheduler.setErrorHandler(new LogAndMetricsErrorHandler());
        return taskScheduler;
    }

    private class LogAndMetricsErrorHandler implements ErrorHandler {
        @Override
        public void handleError(Throwable throwable) {
            log.error("failing on scheduler tasks", throwable);
        }
    }

}
