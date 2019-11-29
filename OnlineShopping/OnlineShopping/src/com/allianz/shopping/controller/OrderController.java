package com.allianz.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allianz.shopping.dao.AddToCardDAOImpl;
import com.allianz.shopping.dao.AddToCartDAO;
import com.allianz.shopping.dao.CustomerDAO;
import com.allianz.shopping.dao.CustomerDAOImpl;
import com.allianz.shopping.dao.OrderDAO;
import com.allianz.shopping.dao.OrderDAOImpl;
import com.allianz.shopping.dao.ProductDAO;
import com.allianz.shopping.dao.ProductDAODatabaseImpl;
import com.allianz.shopping.model.AddToCart;
import com.allianz.shopping.model.Customer;
import com.allianz.shopping.model.Order;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String username = (String)request.getSession().getAttribute("username");
		String orderId = request.getParameter("orderId");
		System.out.println("orderId : " + orderId);
		OrderDAO orderDao = new OrderDAOImpl();
		
		if(action != null && action.equalsIgnoreCase("getAllOrderByUserName"))
		{
			
			System.out.println(username);
			CustomerDAO customerDAO = new CustomerDAOImpl();
			Customer customer = new Customer();
			customer = customerDAO.getCustomerOrderDetails(username);
			System.out.println("customer details :  " + customer);
			
			Order order = orderDao.getOrderByOrderId(Integer.parseInt(orderId));
			
			System.out.println("Order Details are : " + order);
			if(order != null)
			{
				request.setAttribute("customer", customer);
				request.setAttribute("order", order);
			}
			
			
			System.out.println("Add to list " + request.getParameter("addToList"));
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("getAllOrderByUserName.jsp");
			requestDispatcher.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
