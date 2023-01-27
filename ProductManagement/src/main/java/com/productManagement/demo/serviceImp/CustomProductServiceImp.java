package com.productManagement.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Products;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.service.ProductService;

@Service
public class CustomProductServiceImp implements ProductService {

	@Autowired
	 ProductRepository productRepo;
	
	@Override
	public boolean save(Products product) {
		try {
			productRepo.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
