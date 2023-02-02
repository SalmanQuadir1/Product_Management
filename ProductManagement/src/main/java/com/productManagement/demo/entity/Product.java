package com.productManagement.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="status")
	private String status;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="reviews")
	private String reviews;
	
	@Column(name="rating")
	private String rating;
	
	@Column(name="details")
	private String details;
	
	
	
	public Product() {
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



	public String getReviews() {
		return reviews;
	}



	public void setReviews(String reviews) {
		this.reviews = reviews;
	}



	public String getRating() {
		return rating;
	}



	public void setRating(String rating) {
		this.rating = rating;
	}



	public String getDetails() {
		return details;
	}



	public void setDetails(String details) {
		this.details = details;
	}



	public Product(Long id, String productName, String description, Double price, String status, String comments,
			String reviews, String rating, String details) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.status = status;
		this.comments = comments;
		this.reviews = reviews;
		this.rating = rating;
		this.details = details;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", description=" + description + ", price="
				+ price + ", status=" + status + ", comments=" + comments + ", reviews=" + reviews + ", rating="
				+ rating + ", details=" + details + "]";
	}
	
	
	
}
