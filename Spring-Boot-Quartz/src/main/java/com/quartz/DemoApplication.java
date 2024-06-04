package com.quartz;


import com.quartz.task.JobTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemoApplication {
    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(DemoApplication.class);
    }
}

