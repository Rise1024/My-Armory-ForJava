package org.example.cross.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*",allowedHeaders="*",methods={RequestMethod.GET, RequestMethod.POST},allowCredentials = "true")
public class UserController {
	@GetMapping("/test")
	public String hello(Integer age,String name) {
		return "hello";
	}
}
