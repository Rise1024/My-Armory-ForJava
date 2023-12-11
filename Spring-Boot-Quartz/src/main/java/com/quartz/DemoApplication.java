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
        Map<String,Object> map=new HashMap<>();
        map.put("test",32);


        //创建调度器工厂
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();


        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(JobTask.class)
                .setJobData(new JobDataMap(map))
                .withIdentity("jobTask", "group1").build();

        //创建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionNextWithExistingCount())
                .forJob(jobDetail)
                .build();


        // 将 JobDetail 和 Trigger 加入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        //
        scheduler.start();

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(2000);
                System.out.printf(scheduler.getTriggerKeys(GroupMatcher.anyGroup()).toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }



    }
}

