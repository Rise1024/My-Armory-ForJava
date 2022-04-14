package com.example.swagger.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Controller")
@Controller
@RequestMapping("/v1")
public class UserController {

	@ApiIgnore
	@GetMapping("/test")
	public @ResponseBody String hello() {
		return "hello";
	}

	@ApiOperation(value = "test for one", notes = "test for one")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/test/{id}")
	public void getUserById(@PathVariable(value = "id") Long id) {
		return;
	}

	@ApiOperation(value = "test for list", notes = "test for list")
	@GetMapping("/test/list")
	public void getUserList() {
		return;
	}

}
