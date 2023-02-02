package com.productManagement.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @Column(name="total")
    private double total;
    
    @Column(name="order_name")
    private String orderName;
    
    @Column(name="customer_id")
    private Integer customerId;
    
    @Column(name="payment_mode")
    private String paymentMode;
    
    @Column(name="delivery_charges")
    private Float deliveryCharges;
    
    @Column(name="order_status")
    private String orderStatus;
    
    @Column(name="order_date")
    private String orderDate;
    
    @Column(name="delivery_date")
    private Date deliveryDate;
    
    @Column(name="collection_time")
    private Date collectionTime;
    
    @Column(name="delivering_to")
    private String deliveringTo;
    
    @Column(name="transaction")
    private String transaction;
    
    @Column(name="total_DISCOUNT")
    private Float totalDiscount;
    
    @Column(name="total_MRP")
    private Float totalMrpAmount;
    
    @Column(name="total_payable_amount")
    private Float totalPayableAmount;
    
    @Column(name="final_Amount")
    private Float finalAmount;
    
    
    public Order() {}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Float getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(Float deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}

	public String getDeliveringTo() {
		return deliveringTo;
	}

	public void setDeliveringTo(String deliveringTo) {
		this.deliveringTo = deliveringTo;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public Float getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Float totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Float getTotalMrpAmount() {
		return totalMrpAmount;
	}

	public void setTotalMrpAmount(Float totalMrpAmount) {
		this.totalMrpAmount = totalMrpAmount;
	}

	public Float getTotalPayableAmount() {
		return totalPayableAmount;
	}

	public void setTotalPayableAmount(Float totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}

	public Float getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Float finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Order(Long id, Date date, double total, String orderName, Integer customerId, String paymentMode,
			Float deliveryCharges, String orderStatus, String orderDate, Date deliveryDate, Date collectionTime,
			String deliveringTo, String transaction, Float totalDiscount, Float totalMrpAmount,
			Float totalPayableAmount, Float finalAmount) {
		super();
		this.id = id;
		this.date = date;
		this.total = total;
		this.orderName = orderName;
		this.customerId = customerId;
		this.paymentMode = paymentMode;
		this.deliveryCharges = deliveryCharges;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.collectionTime = collectionTime;
		this.deliveringTo = deliveringTo;
		this.transaction = transaction;
		this.totalDiscount = totalDiscount;
		this.totalMrpAmount = totalMrpAmount;
		this.totalPayableAmount = totalPayableAmount;
		this.finalAmount = finalAmount;
	}
	
	
    
    

}
