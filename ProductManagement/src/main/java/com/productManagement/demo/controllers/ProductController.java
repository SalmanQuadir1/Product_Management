package com.productManagement.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.demo.entity.Products;

@RestController 
public class ProductController {
	
	@PostMapping
	public ResponseEntity<?> saveProduct(@RequestBody Products product) {
        
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
}
