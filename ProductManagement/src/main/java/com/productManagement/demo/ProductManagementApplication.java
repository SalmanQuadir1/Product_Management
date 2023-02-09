package com.productManagement.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.productManagement.demo.service.ProductService;

@SpringBootApplication
public class ProductManagementApplication {
	@Autowired
	private ProductService prodService;

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
		System.out.println("Localhost:8087");
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * prodService.deleteAll(); prodService.init();
	 * 
	 * }
	 */

}
