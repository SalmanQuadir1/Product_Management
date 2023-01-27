package com.productManagement.demo.service;

import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Product;

@Service
public interface ProductService {
	
	boolean save(Product product);

}
