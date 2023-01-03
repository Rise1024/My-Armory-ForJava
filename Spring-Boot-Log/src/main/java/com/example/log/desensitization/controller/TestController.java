package com.example.log.desensitization.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

	@GetMapping("/test")
	public String methodOne() {
		log.debug("测试debug日志");
		log.info("测试info日志");
		log.warn("测试warn日志");
		log.error("测试error日志");
		return "hello 鸡你太美";
	}

}