package com.productManagement.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Order;
import com.productManagement.demo.entity.User;
import com.productManagement.demo.repository.UserRepository;
import com.productManagement.demo.service.UserService;

@Service
public class UserServiceImpl implements  UserService {
	@Autowired
	private UserRepository userRepoitory;

	@Override
	public List<User> findAll() {
		return  userRepoitory.findAll();
	}


	@Override
	public Optional<User> findById(Long id) {
		
		return userRepoitory.findById(id);
	}




	@Override
	public List<User> findDistinctByPhone(String phone) {
		
		return userRepoitory.findDistinctByPhone(phone);
	}


	@Override
	public User getUserDetails(String username) {
		// TODO Auto-generated method stub
		return userRepoitory.findByUsername(username);
	}


	@Override
	public User save(User user) {
		
		return userRepoitory.save(user);
	}

}