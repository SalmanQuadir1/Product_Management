package com.productManagement.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.demo.entity.Order;
import com.productManagement.demo.entity.User;
import com.productManagement.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/saveUser")
	public ResponseEntity checkOutOrders(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		User result = userService.save(user);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<User> user = userService.findAll();
		return ResponseEntity.ok(user);
	}

	@GetMapping("/all/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Optional<User> user = userService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/distinctPhone/{phone}")
	public ResponseEntity distinctPhone(HttpServletRequest request,HttpServletResponse response,
			@PathVariable String phone) {
		List<User> users = userService.findDistinctByPhone(phone);
		if(users != null && users.size() == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(true);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(false);
		}
	}
	
	@GetMapping("/getUserDetails/{username}")
	public ResponseEntity getUserDetails(@PathVariable String username,HttpServletRequest request,HttpServletResponse response) {
		User user = userService.getUserDetails(username);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

}
