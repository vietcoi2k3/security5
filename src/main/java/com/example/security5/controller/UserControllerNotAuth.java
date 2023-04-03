package com.example.security5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.security5.Model.User;
import com.example.security5.Service.UserService;

@RequestMapping(value = "/notauth")
@RestController
public class UserControllerNotAuth {
	@Autowired
	UserService userService;
	@RequestMapping(value = "/getuser",method = RequestMethod.GET)
	public User getUser(@RequestParam String username) {
		return userService.getUser(username);
	}
}
