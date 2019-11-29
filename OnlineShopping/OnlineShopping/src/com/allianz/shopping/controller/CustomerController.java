package com.allianz.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.allianz.shopping.dao.CustomerDAO;
import com.allianz.shopping.dao.CustomerDAOImpl;
import com.allianz.shopping.model.Customer;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CustomerDAO customerdao = new CustomerDAOImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		
		if(action != null && action.equals("logout"))
		{	
			request.getSession().invalidate();
			PrintWriter out = response.getWriter();
			out.println("Logged out Successfully.....");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.include(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action1 = request.getParameter("action1");
		if(action1 != null && action1.equals("login"))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			System.out.println(username + " : " + password);
			
			int customer_id = customerdao.login(username, password);
			
			if(customer_id > 0)
			{
				HttpSession session = request.getSession();
				session.setAttribute("customer_id", customer_id);
				session.setAttribute("username", username );
				response.sendRedirect("ProductDBController");
			}
			else
			{
				response.setContentType("text/html;charset=UTF-8");

				PrintWriter out = response.getWriter();
				out.println("Invalid UserName or password");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.include(request, response);
			}
		}
		else if(action1 != null && action1.equals("register"))	
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String phone = request.getParameter("phonenumber");

			Customer customer = new Customer(name, username, password, phone);
			System.out.println(customer);
			if(customerdao.register(customer))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("Registration Failed....");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.include(request, response);
			}
		}
			
		}
		
/*
 * 
		if(action!=null && action.equals("register"))
		{
			
			
		}*/
		
	
	

}
