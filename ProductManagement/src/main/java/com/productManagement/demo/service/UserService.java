package com.productManagement.demo.service;

import java.util.List;
import java.util.Optional;

import com.productManagement.demo.entity.Order;
import com.productManagement.demo.entity.User;

public interface UserService {

	List<User> findAll();


	Optional<User> findById(Long id);


	List<User> findDistinctByPhone(String phone);


	User getUserDetails(String username);


	User save(User user);

}