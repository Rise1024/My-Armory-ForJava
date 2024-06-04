package com.quartz.service;

import com.quartz.task.JobTask;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CloudCallCenterInitRunner implements ApplicationRunner {

    @Autowired
    private Scheduler scheduler;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("test",32);
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(JobTask.class)
                .setJobData(new JobDataMap(map)).withIdentity("jobTask").storeDurably().build();

//        JobDetail jobDetail = JobBuilder.newJob(JobTask.class)
//                .setJobData(new JobDataMap(map))
//                .withIdentity("jobTask", "group1").build();

        scheduler.addJob(jobDetail, true);

        //创建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionNextWithExistingCount())
                .forJob(jobDetail)
                .build();

        // 将 JobDetail 和 Trigger 加入调度器
        scheduler.scheduleJob(trigger);
        //
    }
}