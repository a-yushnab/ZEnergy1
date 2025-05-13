package com.zenergy.controller;

import com.zenergy.model.UserModel;
import com.zenergy.service.LoginService;
import com.zenergy.util.CookieUtil;
import com.zenergy.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginController is responsible for handling login requests. It interacts with
 * the LoginService to authenticate users.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final LoginService loginService;

    /**
     * Constructor initializes the LoginService.
     */
    public LoginController() {
        this.loginService = new LoginService();
    }

    /**
     * Handles GET requests to the login page.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("Inside doGet method");

        System.out.println("Navigating to login page.");
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for user login.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Inside doPost method");


        System.out.println("Attempting login for user: " + username);

        UserModel userModel = new UserModel(username, password);
        Boolean loginStatus = loginService.loginUser(userModel);

        if (loginStatus != null && loginStatus) {
        	// after loginStatus == true
        	SessionUtil.setAttribute(request, "username", username);

        	// then either redirect or forward...
        	if (username.equalsIgnoreCase("admin")) {
        	    response.sendRedirect(request.getContextPath() + "/admindashboard");
        	} else {
        		response.sendRedirect(request.getContextPath() + "/home");

			}

		} else {
			handleLoginFailure(request, response, loginStatus);
		}
    }

    /**
     * Handles login failures by setting attributes and forwarding to the login
     * page.
     *
     * @param request     HttpServletRequest object
     * @param response    HttpServletResponse object
     * @param loginStatus Boolean indicating the login status
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void handleLoginFailure(HttpServletRequest request, HttpServletResponse response, Boolean loginStatus)
            throws ServletException, IOException {
        String errorMessage;
        if (loginStatus == null) {
            errorMessage = "Our server is under maintenance. Please try again later!";
            System.out.println("Login failed due to connection error. Username: " + request.getParameter("username"));
        } else {
            errorMessage = "Invalid Username or Password! Please try again.";
            System.out.println("Invalid login attempt for username: " + request.getParameter("username"));
        }
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }
}
