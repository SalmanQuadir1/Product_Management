package com.productManagement.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.productManagement.demo.entity.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

}
