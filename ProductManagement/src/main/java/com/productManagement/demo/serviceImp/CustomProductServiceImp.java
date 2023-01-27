package com.productManagement.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Product;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.service.ProductService;

@Service
public class CustomProductServiceImp implements ProductService {

	@Autowired
	 private ProductRepository productRepo;
	
	@Override
	public boolean save(Product product) {
		try {
			productRepo.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
