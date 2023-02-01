package com.productManagement.demo.serviceImp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Category;
import com.productManagement.demo.repository.CategoryRepository;
import com.productManagement.demo.service.CategoryService;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository csRepo;

	@Override
	public boolean save(Category category) {
		try {
           csRepo.save(category);
           return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
