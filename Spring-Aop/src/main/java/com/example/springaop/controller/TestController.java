package com.example.springaop.controller;

import com.example.springaop.audit.Audit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Audit(operation="'测试' +$name")
	@GetMapping("/one")
	public void methodOne(String name) {
		System.out.println("test aop");
	}

}
