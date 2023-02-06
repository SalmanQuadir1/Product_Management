package com.productManagement.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.demo.entity.Order;
import com.productManagement.demo.service.OrderServices;

@RestController
public class OrderController {
	@Autowired
	private OrderServices orderService;

	@PostMapping("/checkoutOrders")
	public ResponseEntity<?> checkOutOrders(@RequestBody Order order, HttpServletRequest request,
			HttpServletResponse response) {
		Order result = orderService.save(order);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/{customerId}/getCustomerOrders/{status}")
	public ResponseEntity<?> getCustomerOrders(HttpServletResponse response,HttpServletRequest request,
			@PathVariable("customerId")Long customerId,@PathVariable("status")String status,@RequestParam Integer pageNumber){
		pageNumber = pageNumber == null ? 0: pageNumber;
		Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("id").descending());
		List<Order> result =orderService.getCustomerOrders(customerId,status,pageable);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/{id}/getCustomerOrderStatus")
	public ResponseEntity<?> getCustomerOrderStatus(HttpServletRequest request,HttpServletResponse response,
			@PathVariable Long id){
		String result = orderService.getCustomerOrderStatus(id);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping("/{id}/updateOrderDetailsForOrders")
	public ResponseEntity<?> updateOrderDetailsForOrders(@PathVariable Long id,@RequestBody Order order,HttpServletRequest request,HttpServletResponse response){
		Order result = orderService.updateOrderDetailsForOrders(id,order);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
