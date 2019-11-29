package com.allianz.shopping.dao;

import java.util.ArrayList;
import java.util.List;

import com.allianz.shopping.model.Category;
import com.allianz.shopping.model.Product;
//static import-> we can directly use this class methods directly
import static com.allianz.shopping.utility.DateUtility.*;

public class ProductDAOImpl implements ProductDAO {

	private static List<Product> productList = new ArrayList<Product>();
	static {
		productList.add(new Product(101, "Colgate", "Colgate", 50, convertStringToDate("12/10/2019")));
		productList.add(new Product(102, "Toothbrush", "Toothbrush", 20, convertStringToDate("13/2/2019")));
		productList.add(new Product(103, "Biscute", "Biscute", 20, convertStringToDate("4/10/2018")));
		productList.add(new Product(104, "Cake", "Cake", 450, convertStringToDate("23/4/2019")));
		productList.add(new Product(105, "Dress", "Dress", 1200, convertStringToDate("3/11/2019")));
		productList.add(new Product(106, "shoes", "shoes", 600, /* DateUtility. */convertStringToDate("15/8/2019")));

	}

	@Override
	public boolean addProduct(Product product) {
		System.out.println("AddAllProducts ->");
		productList.add(product);
		return true;
	}

	@Override
	public boolean updateProduct(Product product) {
		System.out.println("UpdateProduct ->");
		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				Product productTemp = productList.get(i);
				if (productTemp.getProductId() == product.getProductId()) {
					productList.set(i, product);
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean deleteProduct(int productId) {
		System.out.println("DeleteProduct ->");
		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				Product productTemp = productList.get(i);
				if (productTemp.getProductId() == productId) {
					productList.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<Product> getAllProducts() {
		System.out.println("GetAllProducts ->");
		return productList;
	}

	@Override
	public List<Product> getAllProductsByName(String namePattern) {
		List<Product> productListTemp = new ArrayList<Product>();
		System.out.println("GetAllProductsByName ->");
		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				Product productTemp = productList.get(i);
				if (productTemp.getDescription().toLowerCase().contains(namePattern.toLowerCase()))
//				if(productTemp.getDescription().equalsIgnoreCase(namePattern))
				{
					productListTemp.add(productTemp);
				}
			}
			return productListTemp;
		}
		return null;
	}

	@Override
	public List<Product> getAllProductsByPrice(float min, float max) {
		List<Product> productListTemp = new ArrayList<Product>();
		System.out.println("GetAllProductsByPrice ->");
		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				Product productTemp = productList.get(i);
				if (productTemp.getPrice() >= min && productTemp.getPrice() <= max) {
					productListTemp.add(productTemp);
				}
			}
			return productListTemp;
		}

		return null;
	}

	@Override
	public Product getAllProductsById(int id) {
		System.out.println("GetAllProductsById ->");

		for (int i = 0; i < productList.size(); i++) {
			Product product = productList.get(i);
			if (product.getProductId() == id) {
				return product;
			}
		}
		return null;
	}

	@Override
	public List<Category> getCategoryByProductId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductByCategoryId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProductNameByProductId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
