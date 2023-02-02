package com.productManagement.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Order;
import com.productManagement.demo.repository.OrderRepository;
import com.productManagement.demo.service.OrderServices;

@Service
public class OrderServiceImpl implements OrderServices {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order save(Order order) {
		
		return orderRepository.save(order);
	}

	}


