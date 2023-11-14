package com.example.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Api(tags = {"一类接口"},description = "一类")
@Controller
@RequestMapping("/v2")
public class UserController2 {
	@ApiOperation(value = "test for string", notes = "test for string")

	@GetMapping("/test")
	public @ResponseBody String hello(HttpServletRequest httpServletRequest) {
		System.out.println(httpServletRequest.getLocale());
		System.out.println(httpServletRequest.getLocale().getLanguage());
		System.out.println(httpServletRequest.getLocale().getDisplayCountry());
		return "hello";
	}

	@ApiOperation(value = "test for one", notes = "test for one")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/test/{id}")
	public Integer getUserById(@PathVariable(value = "id") Long id) {
		return Integer.valueOf(String.valueOf(id));
	}

	@ApiOperation(value = "test for list", notes = "test for list")
	@GetMapping("/test/list")
	public List<Integer> getUserList() {
		return new ArrayList<>();
	}


	@ApiOperation(value = "test for upd", notes = "test for upd")
	@PostMapping("/test/upd")
	public void userUpd(Integer id) {
	}

}
