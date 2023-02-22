package com.productManagement.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productManagement.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findDistinctByPhone(String phone);

	

	User findByUsername(String username);



	List<User> findDistinctByEmail(String email);



	List<User> findDistinctByUsername(String phone);



	User findByLogin(String issuer);

}
