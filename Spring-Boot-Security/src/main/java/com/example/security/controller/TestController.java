package com.example.security.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zds
 * @Description
 * @createTime 2022/3/9 16:12
 */
@RestController
public class TestController {

    @GetMapping("/hello/{user}")
    public String hello(@PathVariable("user") String user) {
        return user+" loadUserByUsername success";
    }
}
