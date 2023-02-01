package com.productManagement.demo.service;

import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Category;

@Service
public interface CategoryService {

	boolean save(Category category);
}
