package com.allianz.shopping.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private int orderId;
	private Date orderDate;
	private String status;
	private String username;
	private float totalPrice;	
	private Customer customer = new Customer();
	private List<AddToCart> addTocartList = new ArrayList<AddToCart>();
	
	public Order(Date orderDate, String status, String username, float totalPrice) {
		super();
		this.orderDate = orderDate;
		this.status = status;
		this.username = username;
		this.totalPrice = totalPrice;
	}
		
	public Order() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public List<AddToCart> getAddTocartList() {
		return addTocartList;
	}

	public void setAddTocartList(List<AddToCart> addTocartList) {
		this.addTocartList = addTocartList;
	}

	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date date) {
		this.orderDate = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float i) {
		this.totalPrice = i;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [orderDate=" + orderDate + ", status=" + status + ", username=" + username + ", totalPrice="
				+ totalPrice + "]";
	}
	
	
}
