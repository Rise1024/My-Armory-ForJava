package com.jdk17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyJDK17Application {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args){
        SpringApplication.run(MyJDK17Application.class, args);
    }

}

