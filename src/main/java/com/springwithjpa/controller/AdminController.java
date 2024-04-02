package com.springwithjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springwithjpa.entities.User;
import com.springwithjpa.repositories.RoleRepo;
import com.springwithjpa.repositories.UserRepo;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserRepo userrepo;

	@Autowired
	RoleRepo rolerepo;

	@Autowired
	PasswordEncoder bcryptpassEncoder;

	@PostMapping("/add")
	ResponseEntity<User> createUser(@RequestBody User user) {

		String ecryptedPassword = bcryptpassEncoder.encode(user.getPassword());
		user.setPassword(ecryptedPassword);

		User newUser = userrepo.save(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@GetMapping("/welcome")
	String welcomeAdmin() {
		return "Admin dashboard";
	}

}
