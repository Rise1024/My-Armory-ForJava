package com.quartz.task;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobTask implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();

        Integer test = mergedJobDataMap.getIntValue("test");

        System.out.println("任务执行了..."+test);
    }
}