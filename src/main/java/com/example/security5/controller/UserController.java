package com.example.security5.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.security5.Model.User;
import com.example.security5.Service.UserService;
import com.example.security5.dto.registerRequestDto;





@RestController
@RequestMapping(value = "/auth")
public class UserController {


	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/hello",method = RequestMethod.POST)
	public String hello() {
		return "hello";
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public registerRequestDto register(@RequestBody registerRequestDto user) {
		return userService.register(user);
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(@RequestBody User user) {
		return userService.login(user);
	}
	
	
}
