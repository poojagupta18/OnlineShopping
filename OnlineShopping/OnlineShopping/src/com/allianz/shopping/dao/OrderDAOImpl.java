package com.allianz.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.allianz.shopping.model.Order;
import com.allianz.shopping.utility.DBUtility;
import com.allianz.shopping.utility.DateUtility;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public int addOrder(Order order) {
		
		String sql = "INSERT INTO orders(order_date, order_status, user_name, total_price) VALUES(?,?,?,?)";
		Connection con = null;
		int orderId = 0;
		
		try {
			con = DBUtility.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1, DateUtility.ConvertUtilToSql(order.getOrderDate()));
			preparedStatement.setString(2, order.getStatus());
			preparedStatement.setString(3, order.getUsername());
			preparedStatement.setFloat(4, order.getTotalPrice());
			
			int result = preparedStatement.executeUpdate();
			System.out.println("Rows updated is " + result);
			if(result > 0)
			{
				try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
				{
					 if (generatedKeys.next()) {
			                orderId = generatedKeys.getInt(1);
			                System.out.println(orderId);
			            }
				}
				
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
		
		return orderId;
	}

	@Override
	public List<Order> getAllOrder() {
		
		List<Order> orderList = new ArrayList<Order>();
		
		String sql = "SELECT * FROM orders";
		AddToCartDAO dao = new AddToCardDAOImpl();
		Connection con = null;
		
		
		 try {
			con = DBUtility.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				Order order = new Order();
				order.setOrderDate(rs.getDate("order_date"));
				order.setOrderId(rs.getInt("order_id"));
				order.setStatus(rs.getString("order_status"));
				order.setUsername(rs.getString("user_name"));
				order.setTotalPrice(rs.getFloat("total_price"));
				order.setAddTocartList(dao.getAllAddToCartByOrderId(rs.getInt("order_id")));
				orderList.add(order);
			}
			
			return orderList;
			
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
	public Order getOrderByOrderId(int order_id) {
		
		String sql = "SELECT * FROM orders WHERE order_id=?";
		Connection con = null;
		AddToCartDAO dao = new AddToCardDAOImpl();
		Order order = new Order();
		
		 try {
			con = DBUtility.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, order_id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				order.setOrderDate(rs.getDate("order_date"));
				order.setOrderId(rs.getInt("order_id"));
				order.setStatus(rs.getString("order_status"));
				order.setUsername(rs.getString("user_name"));
				order.setTotalPrice(rs.getFloat("total_price"));
				order.setAddTocartList(dao.getAllAddToCartByOrderId(rs.getInt("order_id")));
			}
			
			return order;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	public List<Order> getAllOrderByUserName(String userName) {
		String sql = "SELECT * FROM orders WHERE user_name=?";
		Connection con = null;
		AddToCartDAO dao = new AddToCardDAOImpl();
		Order order = new Order();
		List<Order> orderList = new ArrayList<Order>();
		
		 try {
			con = DBUtility.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			CustomerDAOImpl customerDao = new CustomerDAOImpl();
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				order = new Order();
				order.setOrderDate(rs.getDate("order_date"));
				order.setOrderId(rs.getInt("order_id"));
				order.setStatus(rs.getString("order_status"));
				order.setUsername(rs.getString("user_name"));
				order.setTotalPrice(rs.getFloat("total_price"));
				//order.setCustomer(customerDao.getCustomerOrderDetails(userName));
				order.setAddTocartList(dao.getAllAddToCartByOrderId(rs.getInt("order_id")));
				orderList.add(order);
			}
			
			return orderList;
			
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
