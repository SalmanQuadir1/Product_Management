package com.productManagement.demo.service;

import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Products;

@Service
public interface ProductService {
	
	boolean save(Products product);

}
