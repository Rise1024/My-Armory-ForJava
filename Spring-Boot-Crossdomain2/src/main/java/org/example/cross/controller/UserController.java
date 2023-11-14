package org.example.cross.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
public class UserController {
	@GetMapping("/test")
	public String hello() {
		return "test";
	}

}
