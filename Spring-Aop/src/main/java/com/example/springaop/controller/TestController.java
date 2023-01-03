package com.example.springaop.controller;

import com.example.springaop.audit.Audit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

	@GetMapping("/test")
	public String methodOne(String name) {
		Pageable pageable= PageRequest.of(0,6);

		return String.valueOf(pageable.getPageNumber())+pageable.getPageSize();

	}

}
