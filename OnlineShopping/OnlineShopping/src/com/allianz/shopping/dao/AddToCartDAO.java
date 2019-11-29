package com.allianz.shopping.dao;

import java.util.List;

import com.allianz.shopping.model.AddToCart;

public interface AddToCartDAO {

	boolean addToCard(AddToCart addtocart);
	List<AddToCart> getAllAddToCartByOrderId(int order_id);
}
