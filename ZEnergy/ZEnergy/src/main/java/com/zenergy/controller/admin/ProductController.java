package com.zenergy.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.zenergy.service.ProductService;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/products" })
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Session check
        if (request.getSession(false) == null || request.getSession().getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Set product list for view
        request.setAttribute("drinkList", productService.getAllProducts());
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
    }

    // Handle POST for delete product
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Session check
        if (request.getSession(false) == null || request.getSession().getAttribute("username") == null) {
            request.getSession().setAttribute("error", "Session expired. Please login again.");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Role check
        String username = (String) request.getSession().getAttribute("username");
        if (!"admin".equalsIgnoreCase(username)) {
            request.getSession().setAttribute("error", "You are not authorized to perform this action. Please login as admin.");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            try {
                int drinkId = Integer.parseInt(request.getParameter("drinkId"));
                handleDelete(request, response, drinkId);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid drink ID.");
                request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
            }
        }
    }

    // Handles actual deletion
    private void handleDelete(HttpServletRequest request, HttpServletResponse response, int drinkId)
            throws ServletException, IOException {
        boolean success = productService.deleteProduct(drinkId);
        if (success) {
            request.setAttribute("success", "Drink deleted successfully.");
        } else {
            request.setAttribute("error", "Failed to delete drink.");
        }

        // Show updated product list
        request.setAttribute("drinkList", productService.getAllProducts());
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
    }
}
