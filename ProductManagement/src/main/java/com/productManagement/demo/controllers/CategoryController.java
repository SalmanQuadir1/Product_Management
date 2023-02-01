package com.productManagement.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.demo.entity.Category;

@RestController
public class CategoryController {
	
	@PostMapping("/saveCategory")
	public ResponseEntity<?> saveCategory(@RequestBody Category category ) {
		System.out.println("Category Saved !!!");
		System.out.println(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
}
