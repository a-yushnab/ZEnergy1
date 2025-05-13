package com.zenergy.controller.admin;

import com.zenergy.service.DashboardService;
import com.zenergy.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation for handling dashboard-related HTTP requests for users.
 * 
 * This servlet manages interactions with the UserService to fetch user information,
 * handle updates, and manage user data. It forwards requests to appropriate JSP pages 
 * or handles POST actions based on the request parameters.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admindashboard" })
public class adminDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instance of UserService for handling business logic related to users
    private DashboardService dashboardService;

    /**
     * Default constructor initializes the UserService instance.
     */
    public adminDashboardController() {
        this.dashboardService = new DashboardService();
    }

    /**
     * Handles HTTP GET requests by retrieving user information and forwarding
     * the request to the user dashboard JSP page.
     * 
     * @param request  The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object used to return the response.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException      If an input or output error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch all users using DashboardService
        DashboardService dashboardService = new DashboardService();
        List<UserModel> userList = dashboardService.getAllUsersInfo();

        // Set the userList attribute to be used in JSP
        request.setAttribute("userList", userList);

        // Forward the request to the dashboard.jsp page
        request.getRequestDispatcher("/WEB-INF/pages/admindashboard.jsp").forward(request, response);
    }
}