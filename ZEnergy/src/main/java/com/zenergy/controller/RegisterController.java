package com.zenergy.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.zenergy.model.UserModel;
import com.zenergy.service.RegisterService;
import com.zenergy.util.ImageUtil;
import com.zenergy.util.PasswordUtil;
import com.zenergy.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * RegisterController handles user registration requests and processes form
 * submissions. It also manages file uploads and account creation.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ImageUtil imageUtil = new ImageUtil();
    private final RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
            String validationMessage = validateRegistrationForm(req);
            if (validationMessage != null) {
                handleError(req, resp, validationMessage);
                return;
            }

    		 UserModel userModel = extractUserModel(req);
    	        System.out.println("UserModel extracted.");

    	        Boolean isAdded = registerService.addUser(userModel);
    	        System.out.println("User added result: " + isAdded);

    	        if (isAdded == null) {
    	            handleError(req, resp, "Our server is under maintenance. Please try again later!");
    	        } else if (isAdded) {
    	            try {
    	                if (uploadImage(req)) {
    	                    System.out.println("Image upload successful.");
    	                    handleSuccess(req, resp, "Your account is successfully created!", "/WEB-INF/pages/login.jsp");
    	                } else {
    	                    System.out.println("Image upload failed.");
    	                    handleError(req, resp, "Could not upload the image. Please try again later!");
    	                }
    	            } catch (IOException | ServletException e) {
    	                handleError(req, resp, "An error occurred while uploading the image. Please try again later!");
    	                e.printStackTrace();
    	            }
    	        } else {
    	            handleError(req, resp, "Could not register your account. Please try again later!");
    	        }
    	    } catch (Exception e) {
    	        handleError(req, resp, "An unexpected error occurred. Please try again later!");
    	        e.printStackTrace();
    	    }
    	}
    
    private String validateRegistrationForm(HttpServletRequest req) {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("userName");
		String dobStr = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String number = req.getParameter("number");
		String password = req.getParameter("password");
		String retypePassword = req.getParameter("retypePassword");

		// Check for null or empty fields first
		if (ValidationUtil.isNullOrEmpty(firstName))
			return "First name is required.";
		if (ValidationUtil.isNullOrEmpty(lastName))
			return "Last name is required.";
		if (ValidationUtil.isNullOrEmpty(username))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(dobStr))
			return "Date of birth is required.";
		if (ValidationUtil.isNullOrEmpty(gender))
			return "Gender is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(number))
			return "Phone number is required.";
		if (ValidationUtil.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(retypePassword))
			return "Please retype the password.";

		// Convert date of birth
		LocalDate dob;
		try {
			dob = LocalDate.parse(dobStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}

		// Validate fields
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidGender(gender))
			return "Gender must be 'male' or 'female'.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(number))
			return "Phone number must be 10 digits and start with 98.";
		if (!ValidationUtil.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";

		// Check if the date of birth is at least 16 years before today
		if (!ValidationUtil.isAgeAtLeast16(dob))
			return "You must be at least 16 years old to register.";

		try {
			Part image = req.getPart("image");
			if (!ValidationUtil.isValidImageExtension(image))
				return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
		} catch (IOException | ServletException e) {
			return "Error handling image file. Please ensure the file is valid.";
		}

		return null; // All validations passed
	}

    private UserModel extractUserModel(HttpServletRequest req) throws Exception {
    	String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("userName");
        String dobString = req.getParameter("dob");
        LocalDate dob = LocalDate.parse(dobString);
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String number = req.getParameter("number");
        String password = req.getParameter("password");
        password = PasswordUtil.encrypt(userName, password);

        Part image = req.getPart("image");
        String imageUrl = imageUtil.getImageNameFromPart(image);

        return new UserModel(firstName, lastName, userName, dob, gender, email, number, password, imageUrl);
    }

    private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
        Part image = req.getPart("image");
        return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "user");
    }
    	
    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.setAttribute("firstName", req.getParameter("firstName"));
        req.setAttribute("lastName", req.getParameter("lastName"));
        req.setAttribute("userName", req.getParameter("userName"));
        req.setAttribute("dob", req.getParameter("dob"));
        req.setAttribute("gender", req.getParameter("gender"));
        req.setAttribute("email", req.getParameter("email"));
        req.setAttribute("number", req.getParameter("number"));
        req.setAttribute("password", req.getParameter("password"));
        req.setAttribute("retypePassword", req.getParameter("retypePassword"));
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }
}
