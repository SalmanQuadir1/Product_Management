package com.productManagement.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Product;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

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


	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).get();
	}

	@Override
	public Product saveProduct(Product product) {
		Product result = productRepo.save(product);
		return result;
	}

	@Override
	public void deleteProductById(Long id) {
		productRepo.deleteById(id);

	}


	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	

}
