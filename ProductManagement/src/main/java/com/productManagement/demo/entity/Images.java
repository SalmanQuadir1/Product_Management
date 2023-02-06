package com.productManagement.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Images {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	 private String fileName;
	  
	  @Lob
	  private byte[] data;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	public Images() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Images(Long id, String fileName, byte[] data, Product product) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.data = data;
		this.product = product;
	}
	
	

}
