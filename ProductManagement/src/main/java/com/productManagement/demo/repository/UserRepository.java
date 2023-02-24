package com.productManagement.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productManagement.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findDistinctByPhone(String phone);

	

	User findByUsername(String username);



	List<User> findDistinctByEmail(String email);



	List<User> findDistinctByUsername(String phone);



	User findByLogin(String issuer);

    Optional<User> findByEmail(String username);
}
