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

public class CategoryDAOImpl implements CategoryDAO {
	@Override
	public boolean addCategory(Category category) {
		Connection conn = null;
		
		String sql = "insert into category( category_id , category_code  ,category_description) values(?,?,?)";
		try {
			
			
			conn = DBUtility.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			//preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setInt(1, category.getCategoryId());
			preparedStatement.setString(2, category.getCategoryCode());
			preparedStatement.setString(3, category.getCategoryDescription());
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0)
			{
				conn.commit();
				return true;
			}
			
		} catch (SQLException e) {
			if(conn != null)
			{
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		finally
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
	public boolean updateCategory(Category category) {
		Connection conn;
		conn = DBUtility.getConnection();
		String sql = "UPDATE category SET category_code = ? where category_id = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, category.getCategoryCode());
			preparedStatement.setInt(2, category.getCategoryId());
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0)
			{
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
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
	public boolean deleteCategory(int categoryId) {
		Connection con;
		con = DBUtility.getConnection();
		String sql = "delete from category where category_id = ?";
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0)
			{
				return true;
			}
			
			
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
		
		
		return false;
	}

	@Override
	public List<Category> getAllCategory() {
		String sql = "SELECT * FROM category";
		List<Category> categoryListTemp = new ArrayList<Category>();
		Category category;
		Connection con;
		con = DBUtility.getConnection();
		
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryCode(rs.getString("category_code"));
				category.setCategoryDescription(rs.getString("category_description"));
				categoryListTemp.add(category);
			}
			
			return categoryListTemp;
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

	@Override
	public List<Category> getAllCategorysByName(String namePattern) {
		String sql = "SELECT  category_id, LOWER(category_code), category_description  FROM category where category_code = ? ";
		List<Category> categoryListTemp = new ArrayList<Category>();
		Category category;
		Connection con;
		con = DBUtility.getConnection();
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, namePattern.toLowerCase());
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				category = new Category();
				category.setCategoryId(rs.getInt("category_id "));
				category.setCategoryDescription(rs.getString("category_code"));
				category.setCategoryDescription(rs.getString("category_description"));
				categoryListTemp.add(category);
			}
			
			return categoryListTemp;
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

	
	@Override
	public Category getAllCategoryById(int id) {
		String sql = "SELECT * FROM category where category_id = ? ";
		Category category = null;
		Connection con;
		con = DBUtility.getConnection();
		
		
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				category = new Category();
				category.setCategoryId(rs.getInt("category_id "));
				category.setCategoryDescription(rs.getString("category_code"));
				category.setCategoryDescription(rs.getString("category_description"));
			}
			
			return category;
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

	

	@Override
	public Product getProductByCategoryId(int id) {
		Product product = new Product();
		Connection con = DBUtility.getConnection();
		String sql = "select * from product where productId in (select productId from product_category where category_id = ?)";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				product.setProductId(rs.getInt("productId"));
				product.setProductCode(rs.getString("productCode"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setManfDate(rs.getDate("manfDate"));
			}
			
			return product;
		}catch (SQLException e) {
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

	@Override
	public List<Category> getAllCategoryByProductId(int id) {
		Category category = new Category();
		Connection con = DBUtility.getConnection();
		String sql = "select * from category where category_id in (select category_id from product_category where productId = ?)";
		List<Category> categoryList = new ArrayList<Category>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				category.setCategoryCode(rs.getString("category_code"));
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryDescription(rs.getString("category_description"));
				categoryList.add(category);
			}
			
			return categoryList;
		}catch (SQLException e) {
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

	
}
