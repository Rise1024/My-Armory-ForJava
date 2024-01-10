package com.video.stopwatch;

import org.springframework.util.StopWatch;

/**
 * @Author: DS
 * @Date: 2024/1/4 11:12
 * @Description:
 **/

public class StopWatchDemo {
    public static void main(String[] args) {
        StopWatch stopWatch=new StopWatch("dscoding");
        stopWatch.start("test1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
//        System.out.println("用时:"+stopWatch.getTotalTimeMillis());

        stopWatch.start("test2");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        System.out.println("用时:"+stopWatch.prettyPrint());

    }
}
