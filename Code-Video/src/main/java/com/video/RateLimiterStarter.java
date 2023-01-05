package com.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RateLimiterStarter {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RateLimiterStarter.class, args);
    }

}
