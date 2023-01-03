package com.example.springboot.Schedul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ScheduleTask {

    @Scheduled(cron="${kf.scheduled.datamigration.chatmessage.cron:0 0 12 * * ?}")
    public void schedule1() throws InterruptedException {
        log.info("Schedule 1----------");
        TimeUnit.MINUTES.sleep(2);
    }

    @Scheduled(cron="${kf.scheduled.datamigration.chatmessage.cron:0 0 12 * * ?}")
    public void schedule2() throws InterruptedException {
        log.info("Schedule 2----------");
        TimeUnit.MINUTES.sleep(2);

    }

    @Scheduled(cron="${kf.scheduled.datamigration.chatmessage.cron:0 0 12 * * ?}")
    public void schedule3() throws InterruptedException {
        log.info("Schedule 3----------");
        TimeUnit.MINUTES.sleep(1);
    }

    @Scheduled(cron="${kf.scheduled.datamigration.chatmessage.cron:0 0 12 * * ?}")
    public void schedule4() throws InterruptedException {
        log.info("Schedule 4----------");
        TimeUnit.MINUTES.sleep(1);
    }

    @Scheduled(cron="${kf.scheduled.datamigration.chatmessage.cron:0 1 12 * * ?}")
    public void schedule5() throws InterruptedException {
        log.info("Schedule 5----------");
        Thread.sleep(5000);
    }
}
