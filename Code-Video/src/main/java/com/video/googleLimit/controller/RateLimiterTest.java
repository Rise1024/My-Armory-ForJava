package com.video.googleLimit.controller;

import com.video.googleLimit.service.GuavaRateLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: zds
 * @Date: 2023/01/04/17:42
 * @Description:
 */

@RestController
public class RateLimiterTest {
    @Autowired
    private GuavaRateLimiterService guavaRateLimiterService;

    @GetMapping("/test")
    public String test(HttpServletRequest request,HttpServletResponse response) {
        if (!guavaRateLimiterService.tryGetToken()) {
            response.setStatus(500);
            return "请求过于频繁，请求失败！！！";
        }
        response.setStatus(200);
        return "success";
    }


}
