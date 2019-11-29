package com.allianz.shopping.dao;

import com.allianz.shopping.model.Customer;

public interface CustomerDAO {

	boolean register(Customer customer);
	int login(String username, String password);
	Customer getCustomerOrderDetails(String username);
}
