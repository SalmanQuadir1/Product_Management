package com.productManagement.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Product;

@Service
public interface ProductService {
	
	boolean save(Product product);

	

	Product getProductById(Long id);

	Product saveProduct(Product product);

	void deleteProductById(Long id);



	List<Product> findAll();


}
