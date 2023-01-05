package com.example.hystrix.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@DefaultProperties(defaultFallback="helloFallback")
@RequestMapping("/v1")
public class HystrixCommandKey {

	@GetMapping("/test")
	@HystrixCommand(fallbackMethod = "helloFallback")
	public String HystrixCommandKey() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		} finally {
		}
		return "hello";
	}

	@GetMapping("/test1")
	public String hello1() {
		return "hello";
	}

	public String helloFallback() {
		return "请求被熔断了";
	}

}
