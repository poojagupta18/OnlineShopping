package com.allianz.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.allianz.shopping.model.AddToCart;
import com.allianz.shopping.utility.DBUtility;
import com.allianz.shopping.utility.DateUtility;

public class AddToCardDAOImpl implements AddToCartDAO {

	@Override
	public boolean addToCard(AddToCart addtocart) {
		String sql = "INSERT INTO addtocart(order_id, product_id, quantity,Total_price) VALUES(?,?,?, ?)";
		Connection con = null;
		int orderId = 0;
		
		try {
			con = DBUtility.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, addtocart.getOrderId());
			preparedStatement.setInt(2, addtocart.getProductId());
			preparedStatement.setInt(3, addtocart.getQuantity());
			preparedStatement.setFloat(4, addtocart.getTotal());
			
			int result = preparedStatement.executeUpdate();
			System.out.println("Rows updated is " + result);
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
	public List<AddToCart> getAllAddToCartByOrderId(int order_id) {
		
		String sql = "SELECT * FROM addtocart WHERE order_id=?";
		List<AddToCart> addToCartList = new ArrayList<AddToCart>();
		Connection con = null;
		ProductDAO proDao = new ProductDAODatabaseImpl();
		
		try {
			con = DBUtility.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, order_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				AddToCart addToCart = new AddToCart();
				addToCart.setOrderId(resultSet.getInt("order_id"));
				addToCart.setProductId(resultSet.getInt("product_id"));
				addToCart.setQuantity(resultSet.getInt("quantity"));
				addToCart.setAddToCard(resultSet.getInt("addToCart_id"));
				addToCart.setTotal(resultSet.getFloat("Total_price"));
				//addToCart.setProductList(proDao.getAllProductsById(resultSet.getInt("product_id")));
				addToCartList.add(addToCart);
			}
			
			return addToCartList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return null;
	}

}
