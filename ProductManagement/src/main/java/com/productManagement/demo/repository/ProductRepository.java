package com.productManagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productManagement.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	

}
