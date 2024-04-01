package com.springwithjpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/get")
	String get() {
		return "get api";
	}
	
	@GetMapping("/welcome")
	String welcome() {
		return "welcome api";
	}
	@GetMapping("/update")
	String update() {
		return "update api";
	}
}
