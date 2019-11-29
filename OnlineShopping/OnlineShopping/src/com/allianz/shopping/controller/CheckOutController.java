package com.allianz.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
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
 * Servlet implementation class CheckOutController
 */
@WebServlet("/CheckOutController")
public class CheckOutController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	OrderDAO orderDAO = new OrderDAOImpl();
	AddToCartDAO addToCartDAO = new AddToCardDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckOutController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");
		String product_id[] = request.getParameterValues("product_id");

		List<AddToCart> addToCartList = new ArrayList<AddToCart>();

		System.out.println("Username : " + username);
		System.out.println("inside dopost of product_id " + product_id);
		if (product_id != null && product_id.length > 0) {
			float grantTotal = 0;
			for (int i = 0; i < product_id.length; i++) {
				String total = request.getParameter("total" + product_id[i]);
				grantTotal = grantTotal + Float.parseFloat(total);

			}
			Order order = new Order();
			order.setOrderDate(new Date());
			order.setStatus("Ordered");
			order.setUsername(username);
			order.setTotalPrice(grantTotal);

			int order_id = orderDAO.addOrder(order);
			System.out.println("your order id is : " + order_id);
			for (int i = 0; i < product_id.length; i++) {

				if (product_id[i] != null && !product_id[i].trim().equals("")) {
					System.out.println("product_id at index " + i + product_id[i]);
					String quantity = request.getParameter("quantity" + product_id[i]);
					String total = request.getParameter("total" + product_id[i]);
					System.out.println("quantity : " + quantity);

					if (quantity != null) {
						AddToCart addToCart = new AddToCart();
						addToCart.setOrderId(order_id);
						addToCart.setQuantity(Integer.parseInt(quantity));
						addToCart.setProductId(Integer.parseInt(product_id[i]));
						addToCart.setTotal(Float.parseFloat(total));
						if (addToCartDAO.addToCard(addToCart)) {
							addToCartList.add(addToCart);
						}
					}
				}
			}
			
		}

		
		CustomerDAO customerDAO = new CustomerDAOImpl();
		Customer customer = customerDAO.getCustomerOrderDetails(username);
		ProductDAO productdao = new ProductDAODatabaseImpl();
		List<String> productNameList = new ArrayList<String>();
		AddToCartDAO addToCartDAO = new AddToCardDAOImpl();
		
		/*
		 * System.out.println("---------------------------------------------------");
		 * System.out.println("Customer Name :" + customer.getName());
		 * System.out.println("Customer id : " + customer.getCustomerId());
		 * System.out.println("Mobile Number : " + customer.getPhonenumber());
		 * System.out.println("OrderList is : " + customer.getOrderList());
		 * 
		 * System.out.println("******************************"); List<Order> orderList =
		 * customer.getOrderList(); for(Order order: orderList) { int id =
		 * order.getOrderId(); System.out.println("Order id :" + id);
		 * System.out.println("Total price: " + order.getTotalPrice());
		 * System.out.println("Order Date: " + order.getOrderDate()); List <AddToCart>
		 * addToCartList1 = addToCartDAO.getAllAddToCartByOrderId(id);
		 * 
		 * //System.out.println("Add To cart List is : " + addToCartList1 );
		 * 
		 * for(AddToCart obj: addToCartList1) { System.out.println("Product id : " +
		 * obj.getProductId()); System.out.println("Quantity of Product id " +
		 * obj.getProductId() + " is " + obj.getQuantity());
		 * System.out.println("Total Price :" + obj.getTotal()); String productName =
		 * productdao.getProductNameByProductId(obj.getProductId());
		 * System.out.println("Product Name : " + productName);
		 * productNameList.add(productName); }
		 * System.out.println("******************************"); }
		 * 
		 * System.out.println("---------------------------------------------------");
		 */
		
		List <AddToCart> addToCartList1 = null;
		customer.getName();
		customer.getCustomerId();
		customer.getPhonenumber();
		customer.getOrderList();
		
		System.out.println("customer : " + customer);
		
		List<Order> orderList = customer.getOrderList();
		System.out.println("OrderList : " + orderList);
		request.setAttribute("customer", customer);
		request.setAttribute("orderList", orderList);
		
		for(Order order: orderList)
		{
			int id = order.getOrderId();
			order.getTotalPrice();
			order.getOrderDate();
			if(addToCartList1 == null)
			{
				addToCartList1 = addToCartDAO.getAllAddToCartByOrderId(id);
			}
			else
			{
				List<AddToCart> addTocardList2 = addToCartDAO.getAllAddToCartByOrderId(id);
				addToCartList1.addAll(addTocardList2);
			}
			request.setAttribute("addToCartList1", addToCartList1);	
			request.getSession().setAttribute("addToCartList1", "addToCartList1");
			
			
			
			for(AddToCart obj: addToCartList1)
			{
				
				obj.getProductId();
				obj.getQuantity();
				obj.getTotal();
				System.out.println("List of add to cart");
				System.out.println(obj);
				String productName = productdao.getProductNameByProductId(obj.getProductId());
				productNameList.add(productName);
			}
		}
		
		request.setAttribute("productNameList", productNameList);
		request.getSession().setAttribute("productNameList", productNameList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddToCartDisplay.jsp");
		//System.out.println(addToCartList);
		//request.setAttribute("addToCartList", addToCartList);
		
		requestDispatcher.forward(request, response);

	}
}
