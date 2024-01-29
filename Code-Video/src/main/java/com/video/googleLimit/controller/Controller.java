package com.video.googleLimit.controller;

import com.video.googleLimit.service.RedisLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private RedisLimiterService isAcquire;

    //秒杀接口
    @RequestMapping("/order")
    public String killProduct(@RequestParam(required = true) String name) throws Exception {

        // 调用 isAcquire 来判断是否被限流，当前key为“iphone”，60秒只能进行10次
        // 如果返回true，则表示没有到阈值
        // 返回false，则表示60秒内超过10次
        if (isAcquire.acquire("iphone", 10, 60)) {

            System.out.println("业务成功！");
            return "恭喜(" + name + ")，抢到iphone!";
        } else {

            System.out.println("-----------业务被限流");
            return "对不起，你被限流了!";
        }
    }
}