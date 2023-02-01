package com.productManagement.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.demo.entity.Product;
import com.productManagement.demo.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController 
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	 ProductService prodService;
	
	@PostMapping("/saveProduct")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		System.out.println("Product Saved !!!");
		try {
			prodService.save(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(product);
			
		} catch (Exception e) {
			   
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		} 
	
	}
}
