package org.example.cross.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*",allowedHeaders="*",methods={RequestMethod.GET, RequestMethod.POST},allowCredentials = "true")
public class UserController {
	@GetMapping("/test")
	public String hello(Integer age,String name) {
		LocalTime currentTimeOnly = LocalTime.now();
		log.info("hello-------------------"+currentTimeOnly.getHour()+":"+currentTimeOnly.getMinute()+
				":"+currentTimeOnly.getSecond()+":"+currentTimeOnly.getNano() / 1000000);
		return "hello";
	}
}
