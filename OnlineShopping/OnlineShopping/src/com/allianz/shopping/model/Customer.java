package com.allianz.shopping.model;

import java.util.List;

public class Customer {

	private int customerId;
	private String name;
	private String username;
	private String password;
	private String phonenumber;
	private List<Order> orderList;
	
	public Customer() {
		super();
	}

	public Customer(String name, String username, String password, String phonenumber) {
		super();
		//this.customerId = customerId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phonenumber = phonenumber;
	}
	
	
	
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", username=" + username + ", password="
				+ password + ", phonenumber=" + phonenumber + ", orderList=" + orderList + "]";
	}
	
	
	
	
	
}
