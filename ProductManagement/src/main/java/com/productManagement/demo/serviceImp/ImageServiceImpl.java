package com.productManagement.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Images;
import com.productManagement.demo.repository.ImagesRepository;
import com.productManagement.demo.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ImagesRepository imageRepository;
	@Override
	public void save(Images img) {
		imageRepository.save(img);
		
	}
	
	/*
	 * @Override public Optional<Images> findImageById(Long id) { return
	 * imageRepository.findById(id); }
	 */

	@Override
	public Images findById(Long id) {
		return imageRepository.findById(id).get();
	}

	@Override
	public Images getImageById(Long id) {
		return imageRepository.findById(id).get();
	}


	

	


}
