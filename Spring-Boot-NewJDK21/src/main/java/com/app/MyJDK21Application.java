package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 虚拟线程
 */
@RestController
@SpringBootApplication
public class MyJDK21Application {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args){
        SpringApplication.run(MyJDK21Application.class, args);
    }

}

