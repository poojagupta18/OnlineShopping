package com.allianz.shopping.dao;

import java.util.List;

import com.allianz.shopping.model.Category;
import com.allianz.shopping.model.Product;

public interface CategoryDAO {

	public boolean addCategory(Category product);

	public boolean updateCategory(Category product);

	public boolean deleteCategory(int CategoryId);

	public List<Category> getAllCategory();

	public List<Category> getAllCategorysByName(String namePattern);

	//public List<Category> getAllCategoryByPrice(float min, float max);

	public Category getAllCategoryById(int id);
	public  Product getProductByCategoryId(int id);

	public List<Category> getAllCategoryByProductId(int id);
}
