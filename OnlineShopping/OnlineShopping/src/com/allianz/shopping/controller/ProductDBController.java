package com.allianz.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allianz.shopping.dao.CategoryDAO;
import com.allianz.shopping.dao.CategoryDAOImpl;
import com.allianz.shopping.dao.ProductDAO;
import com.allianz.shopping.dao.ProductDAODatabaseImpl;
import com.allianz.shopping.model.Category;
import com.allianz.shopping.model.Product;
import com.allianz.shopping.utility.DateUtility;

/**
 * Servlet implementation class ProductDBController
 */
@WebServlet("/ProductDBController")
public class ProductDBController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// make object of dao
		static ProductDAO dao = new ProductDAODatabaseImpl();
		static CategoryDAO categorydao = new CategoryDAOImpl();
		public ProductDBController() {
			super();
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			String action = request.getParameter("action");
			System.out.println("Inside ProductDBController doget");

			System.out.println(action);

			if (action != null && action.equals("deleteFromCart"))
			{
				System.out.println("Inside deleteFromcart");
				String id = request.getParameter("index");
				List<Product> productList = new ArrayList<Product>();
				
				if(request.getSession().getAttribute("productList") != null)
				{
					System.out.println(id);
					productList = (List<Product>) request.getSession().getAttribute("productList");
					productList.remove(Integer.parseInt(id));
					System.out.println(id + " index removed");
					request.getSession().removeAttribute("productList");
					request.getSession().setAttribute("productList", productList);
					System.out.println(productList);
				}
				System.out.println("productList not exist");
				RequestDispatcher dispatcher = request.getRequestDispatcher("addToCard.jsp");
				dispatcher.forward(request, response);
			} 
			else if (action != null && action.equals("edit"))
			{
				String id = request.getParameter("id");
				Product product = dao.getAllProductsById(Integer.parseInt(id));
				if (product != null)
				{
					request.setAttribute("product", product);
					request.getRequestDispatcher("productDB.jsp").forward(request, response);
				}
			}
			else if (action != null && action.equals("addToCard"))
			{
				String id = request.getParameter("product_id");
				System.out.println("Add to card---> prodcut id " + id);
				int id1 = 0;
				Product product = dao.getAllProductsById(Integer.parseInt(id));
				if (product != null)
				{
					boolean status = false;
					List<Product> productList = new ArrayList<Product>();
					
					if(request.getSession().getAttribute("productList") != null)
					{
						productList = (List<Product>) request.getSession().getAttribute("productList");
						
						for(Product product1: productList)
						{
							id1 = product1.getProductId();
							if(id1 == Integer.parseInt(id))
							{
								status = true;
								break;
							}
						}
						
						if(status == false)
						{
							System.out.println("product1 : id " + id1);
							productList.add(product);
							request.getSession().setAttribute("productList", productList);
						}
					
					}
					else
					{
						productList.add(product);
						request.getSession().setAttribute("productList", productList);
					}
					
					request.setAttribute("productList", productList);
					request.getRequestDispatcher("addToCard.jsp").forward(request, response);
					
					
				}
			}
			else if (action != null && action.equals("new"))
			{
				//new
				
				System.out.println("After clicking add");
				Product product = new Product();
				if (product != null)
				{
					request.setAttribute("product", product);
					request.getRequestDispatcher("productDB.jsp").forward(request, response);
				}
			}
			else if (action != null && action.equals("getProductByCategoryId"))
			{
				//new
				System.out.println("Inside getProductByCategoryId ");
				String category_id = request.getParameter("category_id");
				List<Product> productList = dao.getProductByCategoryId(Integer.parseInt(category_id));
				System.out.println("ProductList by categoryid " + category_id);
				
				//List<Category> categoryList = categorydao.getAllCategory();
				//request.setAttribute("categoryList", categoryList);

				if (productList != null)
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					request.setAttribute("productList", productList);
					//request.setAttribute("categoryList", categoryList);
					dispatcher.forward(request, response);
				}
			}
			else if(action != null && action.equals("getProductDetails"))
			{
				String productId = request.getParameter("product_id");
				System.out.println("getProductDetails : " + productId);
				
				Product product = dao.getAllProductsById(Integer.parseInt(productId));
				
				if(product != null)
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("subpage.jsp");
					request.setAttribute("product", product);
					dispatcher.forward(request, response);
				}
				else
				{
					System.out.println("product not ");
				}
				
			}

			else {

				List<Product> productList = dao.getAllProducts();
				List<Category> categoryList = categorydao.getAllCategory();

				System.out.println(" Inside else part of Controller "+ productList);
				request.setAttribute("productList", productList);
				request.setAttribute("categoryList", categoryList);
				
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			//doGet(request, response);
			String action=request.getParameter("action");
			System.out.println("Inside ProductDBController doPost");
			System.out.println("Action is " + action);
			PrintWriter out = response.getWriter();
			
			if(action!=null && action.equals("update"))
			{

				System.out.println("Inside update");
				String id=request.getParameter("productId");
				String code=request.getParameter("productCode");
				String description=request.getParameter("description");
				String price=request.getParameter("price");
				String manfDate=request.getParameter("manfDate");
				
				Product product=new Product();
				product.setProductId(Integer.parseInt(id));
				product.setProductCode(code);
				product.setDescription(description);
				product.setPrice(Float.parseFloat(price));
				product.setManfDate(DateUtility.convertStringToDate(manfDate));
				
				boolean flag=false;/*=dao.updateProduct(product);*/
				
				System.out.println(dao.getAllProductsById(Integer.parseInt(id)));
				
				if(dao.getAllProductsById(Integer.parseInt(id)) != null)
					flag = dao.updateProduct(product);
				else
					flag = dao.addProduct(product);
				if(flag)
					//response.sendRedirect("productDB");
					out.print("added successfully");
				
			}
			
			if(action != null && action.equals("save"))
			{
				//int productId = Integer.parseInt(request.getParameter("productId"));
				String productCode = request.getParameter("productCode");
				String description = request.getParameter("description");
				String price = request.getParameter("price");
				String date =request.getParameter("manfDate");
				
				//System.out.println("Id : " + productId );
				System.out.println("code : " + productCode );
				System.out.println("Description : " + description  );
				System.out.println("price : " + price  );
				System.out.println("date : " + date );
				
				
				Product product = new Product();
			//	product.setProductId(productId);
				product.setProductCode(productCode);
				product.setDescription(description);
				product.setPrice(Float.parseFloat(price));
				product.setManfDate(DateUtility.convertStringToDate(date));
				
				System.out.println(product);
				
				boolean flag = false;
				
				//System.out.println("dao.getAllProductsById(productId) " + dao.getAllProductsById(productId) );
				//System.out.println("dao.addProduct(product) : " + dao.addProduct(product));
				/*if(dao.getAllProductsById(productId) != null)
				{
					flag = dao.updateProduct(product);
				}
				else
				{*/
					flag = dao.addProduct(product);
				
				if(flag)
					
					out.print("Added to database");
					//response.sendRedirect("productListDB.jsp");
			}
		}

	}
/*
 * <tr> <td>ProductId</td> <td><input type="text" name="productId"
 * id="productId" value="${product.productId}"/></td> </tr>
 */
