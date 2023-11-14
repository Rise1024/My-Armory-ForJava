package com.example.swagger.controller;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Api(tags = {"二类接口"},description = "二类")
@Controller
@RequestMapping("/v1")
public class UserController {
	@ApiOperation(value = "test for string", notes = "test for string")
	@GetMapping("/test")
	public Boolean hello(Boolean flag) {
		if (flag){
			System.out.println(flag);
		}
		return flag;
	}

	@ApiOperation(value = "test for one", notes = "test for one")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path",example="1")
	@GetMapping("/test/{id}")
	public Integer getUserById(@PathVariable(value = "id") Long id) {
		return Integer.valueOf(String.valueOf(id));
	}

	@ApiOperation(value = "test for list", notes = "test for list")
	@GetMapping("/test/list")
	public List<Integer> getUserList() {
		return new ArrayList<>();
	}

}
