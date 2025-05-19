package com.zenergy.controller;

import com.zenergy.model.DrinkModel;
import com.zenergy.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author Ayushnab Poudel
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/home"})
public class homeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// Fetch product list from database
		ProductService productService = new ProductService();
		List<DrinkModel> drinkList = productService.getAllProducts();

		// Set the product list as a request attribute
		request.setAttribute("drinkList", drinkList);

		// Forward to home.jsp (in WEB-INF)
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}
}
