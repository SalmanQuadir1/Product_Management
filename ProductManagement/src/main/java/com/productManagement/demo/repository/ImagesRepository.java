package com.productManagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productManagement.demo.entity.Images;

public interface ImagesRepository extends JpaRepository<Images, Long> {

}
