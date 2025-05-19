package com.zenergy.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.zenergy.model.UserModel;
import com.zenergy.service.DashboardService;
import com.zenergy.util.ValidationUtil;

/**
 * Servlet implementation class StudentController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/modifyStudents" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Instance of DashboardService for handling business logic
	private DashboardService dashboardService;

	/**
	 * Default constructor initializes the DashboardService instance.
	 */
	public userController() {
		this.dashboardService = new DashboardService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve all student information from the DashboardService
		request.setAttribute("userList", dashboardService.getAllUsersInfo());
		

		// Forward the request to the students JSP for rendering
		request.getRequestDispatcher("/WEB-INF/pages/admindashboard.jsp").forward(request, response);
	}
}