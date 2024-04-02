package com.springwithjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springwithjpa.entities.User;
import com.springwithjpa.repositories.UserRepo;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepo userrepo;

	@GetMapping("/get")
	ResponseEntity<List<User>> get() {
		List<User> userlist = userrepo.findAll();
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}

	@GetMapping("/welcome")
	String welcome() {
		return "welcome api";
	}

	
}
