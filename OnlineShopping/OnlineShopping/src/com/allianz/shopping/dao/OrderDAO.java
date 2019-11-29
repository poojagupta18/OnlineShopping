package com.allianz.shopping.dao;

import java.util.List;

import com.allianz.shopping.model.Order;

public interface OrderDAO {

	public int addOrder(Order order);
	public List<Order> getAllOrder();
	public Order getOrderByOrderId(int order_id);
	public List<Order> getAllOrderByUserName(String userName);
	
}
