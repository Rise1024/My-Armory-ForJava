package org.example.cross.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
//@CrossOrigin(origins = "*")
public class UserController {
	@GetMapping("/test")
	public String hello(Integer age,String name) {
		return "hello";
	}
	@PostMapping("/test")
	public String hello1(Integer age,String name) {
		return "hello1";
	}

}
