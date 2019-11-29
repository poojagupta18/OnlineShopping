package com.allianz.shopping.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {

	private int productId;
	private String productCode;
	private String description;
	private float price;
	private Date manfDate;
	private List<Category> categoryList = new ArrayList<Category>();
	

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getManfDate() {
		return manfDate;
	}

	public void setManfDate(Date manfDate) {
		this.manfDate = manfDate;
	}

	public Product() {
		super();
	}

	public Product(int productId, String productCode, String description, float price, Date manfDate) {
		super();
		this.productId = productId;
		this.productCode = productCode;
		this.description = description;
		this.price = price;
		this.manfDate = manfDate;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productCode=" + productCode + ", description=" + description
				+ ", price=" + price + ", manfDate=" + manfDate + "]";
	}

}
