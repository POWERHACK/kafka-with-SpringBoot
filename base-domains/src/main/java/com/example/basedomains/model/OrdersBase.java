package com.example.basedomains.model;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class OrdersBase implements Serializable{

	@Id
	private String id;
	private String customerName;
	private String productNaame;
	private int qty;
	public OrdersBase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrdersBase(String id, String customerName, String productNaame, int qty) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.productNaame = productNaame;
		this.qty = qty;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductNaame() {
		return productNaame;
	}
	public void setProductNaame(String productNaame) {
		this.productNaame = productNaame;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", customerName=" + customerName + ", productNaame=" + productNaame + ", Qty=" + qty
				+ "]";
	}
	
	
	
	
}
