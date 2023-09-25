package com.example.emailservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OrderStatus{
	@Id
	private String orderId;
	private String productName;
	private String consumerName;
	private String status;
	private String message;
	public OrderStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderStatus(String orderId, String productName, String consumerName, String status, String message) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.consumerName = consumerName;
		this.status = status;
		this.message = message;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "OrderStatus [orderId=" + orderId + ", productName=" + productName + ", consumerName=" + consumerName
				+ ", status=" + status + ", message=" + message + "]";
	}
	
	
	
}