package com.productManagement.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User user;
	
//	@OneToOne
//	private Order order;
	
	
	private String quantity;
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	 * 
	 * @OneToOne private User user;
	 * 
	 * @OneToOne private Order order;
	 * 
	 * @OneToMany private Category category;
	 * 
	 * private String quantity;
	 */
	
}
