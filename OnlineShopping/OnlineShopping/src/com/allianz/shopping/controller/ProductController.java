package com.allianz.shopping.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.allianz.shopping.dao.ProductDAO;
import com.allianz.shopping.dao.ProductDAOImpl;
import com.allianz.shopping.model.Product;
import com.allianz.shopping.utility.DateUtility;

@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// make object of dao
	static ProductDAO dao = new ProductDAOImpl();

	public ProductController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action != null && action.equals("delete")) {
			String id = request.getParameter("id");
			boolean flag = dao.deleteProduct(Integer.parseInt(id));
			if (flag) {
				response.sendRedirect("ProductController");
			}
		} else if (action != null && action.equals("edit")) {
			String id = request.getParameter("id");
			Product product = dao.getAllProductsById(Integer.parseInt(id));
			if (product != null) {
				request.setAttribute("product", product);
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}
		}else if (action != null && action.equals("new")) {
			//new
			Product product = new Product();
			if (product != null) {
				request.setAttribute("product", product);
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}
		}else if (action != null && action.equals("deletecart")) {
			//new
			Product product = new Product();
			if (product != null) {
				request.setAttribute("product", product);
				request.getRequestDispatcher("product.jsp").forward(request, response);
			}
		}

		else {

			List<Product> productList = dao.getAllProducts();
			System.out.println(productList);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("productList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doGet(request, response);
		String action=request.getParameter("action");
		
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
		
		
		if(action!=null && action.equals("update"))
		{
			
			boolean flag=false;/*=dao.updateProduct(product);*/
			if(dao.getAllProductsById(Integer.parseInt(id))!=null)
				flag=dao.updateProduct(product);
			else
				flag=dao.addProduct(product);
			if(flag)
				response.sendRedirect("Product");			
			
		}
		
	}

}
