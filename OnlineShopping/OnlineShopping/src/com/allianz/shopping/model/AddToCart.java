package com.allianz.shopping.model;

import java.util.List;

public class AddToCart {

	private int AddToCardId;
	private int orderId;
	private int quantity;
	private int productId;
	private float total;
	//private List<Product> productList;
	
	public AddToCart( int orderId, int quantity, int productId) {
		super();
		this.orderId = orderId;
		this.quantity = quantity;
		this.productId = productId;
	}
	
	
	
	/*
	 * public int getAddToCardId() { return AddToCardId; }
	 * 
	 * 
	 * 
	 * public void setAddToCardId(int addToCardId) { AddToCardId = addToCardId; }
	 */


	/*
	 * public List<Product> getProductList() { return productList; }
	 * 
	 * 
	 * 
	 * public void setProductList(List<Product> productList) { this.productList =
	 * productList; }
	 */


	public float getTotal() {
		return total;
	}



	public void setTotal(float total) {
		this.total = total;
	}



	public AddToCart() {
		super();
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getAddToCard() {
		return AddToCardId;
	}
	public void setAddToCard(int addToCard) {
		AddToCardId = addToCard;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}



	@Override
	public String toString() {
		return "AddToCart [AddToCardId=" + AddToCardId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", productId=" + productId + ", total=" + total + "]";
	}
	
	
	
	
	
}
