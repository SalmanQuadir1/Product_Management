package com.productManagement.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.demo.entity.Order;
import com.productManagement.demo.service.OrderServices;

@RestController
public class OrderController {
	@Autowired
	private OrderServices orderService;

	@PostMapping("/checkoutOrders")
	public ResponseEntity checkOutOrders(@RequestBody Order order, HttpServletRequest request,
			HttpServletResponse response) {
		Order result = orderService.save(order);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
