package com.allianz.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.allianz.shopping.model.Customer;
import com.allianz.shopping.utility.DBUtility;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public boolean register(Customer customer) {
		System.out.println("inside register");
		Connection conn = null;
		String sql = "insert into customer(name, username, password ,phonenumber) values(?,?,?,?)";
		try {
			
			
			conn = DBUtility.getConnection();
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			//preparedStatement.setInt(1, customer.getCustomerId());
			preparedStatement.setString(1, customer.getName());	
			preparedStatement.setString(2, customer.getUsername());
			preparedStatement.setString(3, customer.getPassword());
			preparedStatement.setString(4, customer.getPhonenumber());
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0)
			{
				System.out.println("user added");
				return true;
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("user not added");

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
	public int login(String username, String password) {
		String sql = "SELECT * from customer where username=? and password=?";
		Connection con = DBUtility.getConnection();
		int id = 0;
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				id = rs.getInt("customer_id");
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Invalid user");	
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
		
		return id;
	}

	@Override
	public Customer getCustomerOrderDetails(String username) {
		String sql = "SELECT * from customer where username=?";
		Connection con = DBUtility.getConnection();
		
		OrderDAO dao = new OrderDAOImpl();
		int id = 0;
		Customer customer = null;
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
		
			if(rs.next())
			{
				customer = new Customer();
				customer.setCustomerId(rs.getInt("customer_id"));
				customer.setName(rs.getString("name"));
				System.out.println("Fetched name is " + rs.getString("name"));
				customer.setOrderList(dao.getAllOrderByUserName(username));
				customer.setPhonenumber(rs.getString("phonenumber"));
			}
			preparedStatement.close();
			rs.close();
			
		} catch (SQLException e) {

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
		
		return customer;
	}

}
