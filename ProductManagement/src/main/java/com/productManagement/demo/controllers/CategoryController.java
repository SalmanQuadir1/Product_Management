package com.productManagement.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.demo.entity.Category;
import com.productManagement.demo.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService cs;

	@PostMapping("/saveCategory")
	public ResponseEntity<?> saveCategory(@RequestBody Category category) {
		try {
			cs.save(category);
			return ResponseEntity.status(HttpStatus.CREATED).body(category);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
}
