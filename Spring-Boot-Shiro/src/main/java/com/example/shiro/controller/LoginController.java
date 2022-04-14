package com.example.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

	@GetMapping("/index")
	public String login() {
		return "index";
	}

	@GetMapping("/login/{username}/{password}")
	@ResponseBody
	public String login( String username,String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return "hello success";
		} catch (UnknownAccountException e) {
			return "hello success"+e.getMessage();
		} catch (IncorrectCredentialsException e) {
			return "hello success"+e.getMessage();
		} catch (LockedAccountException e) {
			return "hello success"+e.getMessage();
		} catch (AuthenticationException e) {
			return "hello success"+e.getMessage();
		}
	}
}
