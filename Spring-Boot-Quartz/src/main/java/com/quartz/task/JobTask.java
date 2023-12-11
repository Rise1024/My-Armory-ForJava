package com.quartz.task;

import com.quartz.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class JobTask implements Job {

    @Autowired
    HttpService httpService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        httpService.test();

        Integer test = mergedJobDataMap.getIntValue("test");

        System.out.println("任务执行了..."+test);


    }
}