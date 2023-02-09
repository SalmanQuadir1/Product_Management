package com.productManagement.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import models.ProductVariantDto;

@Entity
@Table(name="product")
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
	
	/*
	 * @ElementCollection private List<String> sizes;
	 * 
	 * @ElementCollection private List<Double> weights;
	 * 
	 * @ElementCollection private List<Integer> quantities;
	 */
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	

	@Column(name="images")
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	 private List<Images> images;
	
	@Column(name="variants")
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<ProductVariant> variants;
	
	
	public Product() {
		
	}


	  @JsonIgnore
	  public Product getProduct() {
	    return this;
	  }

	public List<Images> getImages() {
		return images;
	}




	public void setImages(List<Images> images) {
		this.images = images;
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
	


	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}

	


	public List<ProductVariant> getVariants() {
		return variants;
	}


	public void setVariants(List<ProductVariant> variants) {
		this.variants = variants;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", description=" + description + ", price="
				+ price + ", status=" + status + ", comments=" + comments + ", reviews=" + reviews + ", rating="
				+ rating + ", details=" + details + "]";
	}





	
	
	
}
