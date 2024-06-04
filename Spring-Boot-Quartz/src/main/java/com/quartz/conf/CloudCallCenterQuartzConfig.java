package com.quartz.conf;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.util.Properties;

@Configuration
public class CloudCallCenterQuartzConfig {
    @Value("${ccc.quartz.thread-count:10}")
    private int threadCount;
    @Autowired
    private ApplicationContext applicationContext;

    public CloudCallCenterQuartzConfig() {
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory beanJobFactory = new AutoWiringSpringBeanJobFactory();
        beanJobFactory.setApplicationContext(applicationContext);
        return beanJobFactory;
    }

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        Properties properties = new Properties();
        properties.put("org.quartz.threadPool.threadCount", String.valueOf(threadCount));
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(properties);
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.setJobFactory(springBeanJobFactory());
//        scheduler.start();
        return scheduler;
    }
}