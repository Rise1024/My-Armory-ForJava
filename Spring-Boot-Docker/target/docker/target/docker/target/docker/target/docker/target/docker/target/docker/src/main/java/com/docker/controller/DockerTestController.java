package com.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zds
 * @Date: 2023/01/16/16:32
 * @Description:
 */

@RestController
public class DockerTestController {

    @GetMapping("/test")
    private String test(){
        return "docker test";
    }
}
