package com.video.smartdoc.controller;

import com.video.smartdoc.data.SmartData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zds
 * @Date: 2023/01/05/22:55
 * @Description:
 */
@RestController
public class SmartController {

    /**
     * 这是一个查询接口
     * @return
     */
    @GetMapping("/smart")
    public SmartData test(){
        return new SmartData("张三",24,true);
    }

    /**
     * 这是一个创建接口
     * @param smartData
     * @return
     */
    @PostMapping("/smart1")
    public SmartData test1(@RequestBody SmartData smartData){
        return smartData;
    }
}
