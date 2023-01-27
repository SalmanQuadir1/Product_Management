package com.productManagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productManagement.demo.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{

	

}
