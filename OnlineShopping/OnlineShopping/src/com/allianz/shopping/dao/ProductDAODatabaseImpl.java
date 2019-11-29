package com.allianz.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.allianz.shopping.model.Category;
import com.allianz.shopping.model.Product;
import com.allianz.shopping.utility.DBUtility;
import com.allianz.shopping.utility.DateUtility;

public class ProductDAODatabaseImpl implements ProductDAO {

	@Override
	public boolean addProduct(Product product) {

		Connection conn = null;

		String sql = "insert into product( productCode , description ,price,manfDate) values(?,?,?,?)";
		try {

			conn = DBUtility.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			// preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setString(1, product.getProductCode());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setFloat(3, product.getPrice());
			preparedStatement.setDate(4, DateUtility.ConvertUtilToSql(product.getManfDate()));

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				conn.commit();
				return true;
			}

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		Connection conn;
		conn = DBUtility.getConnection();
		String sql = "UPDATE product SET productCode = ? where productId = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, product.getProductCode());
			preparedStatement.setInt(2, product.getProductId());

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		return false;
	}

	@Override
	public boolean deleteProduct(int productId) {
		Connection con;
		con = DBUtility.getConnection();
		String sql = "delete from product where productId = ?";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, productId);

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		return false;
	}

	@Override
	public List<Product> getAllProducts() {
		String sql = "SELECT * FROM product";
		List<Product> productListTemp = new ArrayList<Product>();
		Product productTemp;
		Connection con;
		con = DBUtility.getConnection();
		CategoryDAO categoryDAO = new CategoryDAOImpl();

		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				productTemp = new Product();
				productTemp.setProductId(rs.getInt("productId"));
				productTemp.setProductCode(rs.getString("productCode"));
				productTemp.setDescription(rs.getString("description"));
				productTemp.setPrice(rs.getFloat("price"));
				productTemp.setManfDate(rs.getDate("manfDate"));
				productTemp.setCategoryList((categoryDAO.getAllCategoryByProductId(productTemp.getProductId())));
				productListTemp.add(productTemp);
			}

			return productListTemp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public List<Product> getAllProductsByName(String namePattern) {
		String sql = "SELECT LOWER(productCode) from product where productCode = %?%";
		List<Product> productListTemp = new ArrayList<Product>();
		Connection con = DBUtility.getConnection();

		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, namePattern.toLowerCase());
			ResultSet rs = preparedStatement.executeQuery(sql);

			while (rs.next()) {
				Product productTemp = new Product();
				productTemp.setProductId(rs.getInt("productId"));
				productTemp.setProductCode(rs.getString("productCode"));
				productTemp.setDescription(rs.getString("description"));
				productTemp.setPrice(rs.getFloat("price"));
				productTemp.setManfDate(rs.getDate("manfDate"));
				productListTemp.add(productTemp);
			}

			return productListTemp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		return null;
	}

	@Override
	public List<Product> getAllProductsByPrice(float min, float max) {
		Connection con;
		con = DBUtility.getConnection();
		String sql = "select * from product where price >= ? and price <= ?";
		List<Product> productListTemp = new ArrayList<Product>();

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setFloat(1, min);
			preparedStatement.setFloat(2, max);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Product productTemp = new Product();
				productTemp.setProductId(rs.getInt("productId"));
				productTemp.setProductCode(rs.getString("productCode"));
				productTemp.setDescription(rs.getString("description"));
				productTemp.setPrice(rs.getFloat("price"));
				productTemp.setManfDate(rs.getDate("manfDate"));
				productListTemp.add(productTemp);
			}

			return productListTemp;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public Product getAllProductsById(int id) {
		Product product = new Product();
		Connection con = DBUtility.getConnection();
		String sql = "select * from product where productId = ?";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				product.setProductId(rs.getInt("productId"));
				product.setProductCode(rs.getString("productCode"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setManfDate(rs.getDate("manfDate"));
			}
			product.setCategoryList(getCategoryByProductId(id));
			return product;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/*
	 * @Override public Product getCategoryByProductId(int id) { /* Product product
	 * = new Product(); Connection con = DBUtility.getConnection(); String sql =
	 * "select * from category where category_id in (select category_id from product_category where product_id = ?)"
	 * ;
	 * 
	 * PreparedStatement preparedStatement; try { preparedStatement =
	 * con.prepareStatement(sql); preparedStatement.setInt(1, id);
	 * 
	 * ResultSet rs = preparedStatement.executeQuery();
	 * 
	 * if(rs.next()) { product.setProductId(rs.getInt("productId"));
	 * product.setProductCode(rs.getString("productCode"));
	 * product.setDescription(rs.getString("description"));
	 * product.setPrice(rs.getFloat("price"));
	 * product.setManfDate(rs.getDate("manfDate")); }
	 * 
	 * return product; }catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return null
	 */

	

	@Override
	public List<Product> getProductByCategoryId(int id) {

		Connection con = DBUtility.getConnection();
		String sql = "select * from product where productId in (select productId from product_category where category_id = ?)";
		List<Product> productList = new ArrayList<Product>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("productId"));
				product.setProductCode(rs.getString("productCode"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setManfDate(rs.getDate("manfDate"));
				productList.add(product);
			}

			return productList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		return null;
	}

	@Override
	public List<Category> getCategoryByProductId(int id) {
		
		List<Category> categoryList = new ArrayList<Category>();
		
		Connection con = DBUtility.getConnection();
		String sql = "select * from category where category_id in (select category_id from product_category where productId=?)" ;
		 PreparedStatement preparedStatement;
		 try
		 { 
			 preparedStatement = con.prepareStatement(sql);
			 preparedStatement.setInt(1, id);
			 ResultSet rs = preparedStatement.executeQuery();
			
			 while(rs.next())
			 {
				String categoryId = rs.getString("category_id");
				String categoryCode = rs.getString("category_code");
				String categoryDescription = rs.getString("category_description");
				Category category =	 new Category(Integer.parseInt(categoryId), categoryCode,categoryDescription );
				categoryList.add(category);
			 }
		 return categoryList;
		 
		 }catch (SQLException e) { // TODO Auto-generated catch block
				  e.printStackTrace(); }
		 finally
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				  
		 return null;
	}

	@Override
	public String getProductNameByProductId(int id) {
		Connection con = DBUtility.getConnection();
		String sql = "select * from product where productId = ?";
		String str = null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				
				str = rs.getString("productCode");
			}
			;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return str;
	}

}
