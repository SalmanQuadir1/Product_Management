package com.productManagement.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.demo.entity.Product;
import com.productManagement.demo.entity.User;
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


	@PostMapping("/updateProduct")
	public ResponseEntity updateProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Product product) {
		if (product != null && product.getId() != null) {
			Product productEntity = prodService.getProductById(product.getId());
			productEntity.setDescription(productEntity.getDescription());
			Product result = prodService.saveProduct(productEntity);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else if (product != null && product.getId() == null) {
			Product productEntity = new Product();
			productEntity.setProductName(product.getProductName());
			productEntity.setId(product.getId());
			productEntity.setDescription(product.getDescription());
			productEntity.setPrice(product.getPrice());

			Product newProduct = prodService.saveProduct(productEntity);

			// add product items
			return ResponseEntity.status(HttpStatus.OK).body(newProduct);

		} else {
			return ResponseEntity.badRequest().body("Product Details Are missing");
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<Product> product = prodService.findAll();
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<List<Product>> deleteProductItem(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		prodService.deleteProductById(id);
		return ResponseEntity.ok().build();
	}

}
