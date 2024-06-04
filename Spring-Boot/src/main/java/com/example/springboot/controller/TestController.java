package com.example.springboot.controller;

import com.example.springboot.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: DS
 * @Date: 2024/5/24 15:43
 * @Description:
 **/
@RestController
public class TestController {
    private final ITestService testService;
    @Autowired
    public TestController(ITestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public String testCon(){
       return testService.test();
    }
}
