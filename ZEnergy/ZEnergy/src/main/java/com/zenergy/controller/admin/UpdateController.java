package com.zenergy.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.zenergy.model.DrinkModel;
import com.zenergy.service.UpdateService;
import com.zenergy.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(asyncSupported = true, urlPatterns = { "/drinkUpdate" })
@MultipartConfig
public class UpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final UpdateService updateService = new UpdateService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Session check
        if (req.getSession(false) == null || req.getSession().getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String drinkIdStr = req.getParameter("drinkId");
        DrinkModel drink = null;

        if (drinkIdStr != null && !drinkIdStr.isEmpty()) {
            try {
                int drinkId = Integer.parseInt(drinkIdStr);
                drink = updateService.getProductById(drinkId);
                if (drink == null) {
                    req.setAttribute("error", "Drink not found for ID: " + drinkId);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid drink ID format.");
            }
        }

        if (drink == null && req.getSession().getAttribute("drink") != null) {
            drink = (DrinkModel) SessionUtil.getAttribute(req, "drink");
            SessionUtil.removeAttribute(req, "drink");
        }

        req.setAttribute("drink", drink);
        req.getRequestDispatcher("/WEB-INF/pages/updateDrinks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        //️ Session check
        if (req.getSession(false) == null || req.getSession().getAttribute("username") == null) {
            req.setAttribute("error", "Session expired or not logged in. Please log in again.");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Retrieve username from session
        String username = (String) req.getSession().getAttribute("username");

        // Restrict only to admin
        
        if (!"admin".equalsIgnoreCase(username)) {
            req.getSession().setAttribute("error", "You are not authorized to perform this action. Please login as admin.");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            String brandName = req.getParameter("brandName");
            String drinkName = req.getParameter("drinkName");
            double price = Double.parseDouble(req.getParameter("price"));
            String flavor = req.getParameter("flavor");
            int calorie = Integer.parseInt(req.getParameter("calorie"));
            String drinkIdStr = req.getParameter("drinkId");
            int drinkId = (drinkIdStr != null && !drinkIdStr.isEmpty()) ? Integer.parseInt(drinkIdStr) : 0;

            // Image handling
            Part imagePart = req.getPart("image");
            String imagePath = null;

            if (imagePart != null && imagePart.getSize() > 0) {
                String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                String uploadDir = getServletContext().getRealPath("/uploads");
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }

                String filePath = uploadDir + File.separator + fileName;
                imagePart.write(filePath);

                imagePath = "uploads/" + fileName;
            } else {
                DrinkModel existing = updateService.getProductById(drinkId);
                imagePath = (existing != null) ? existing.getImagePath() : null;
            }

            DrinkModel drink = new DrinkModel(brandName, drinkName, price, flavor, calorie, username, imagePath);
            drink.setDrinkId(drinkId);

            Boolean result = updateService.updateDrinkInfo(drink);

            if (result != null && result) {
                resp.sendRedirect(req.getContextPath() + "/products");
            } else {
                req.getSession().setAttribute("drink", drink);
                handleUpdateFailure(req, resp, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An unexpected error occurred. Please try again.");
            req.getRequestDispatcher("/WEB-INF/pages/updateDrinks.jsp").forward(req, resp);
        }
    }

    private void handleUpdateFailure(HttpServletRequest req, HttpServletResponse resp, Boolean result)
            throws ServletException, IOException {
        String errorMessage;
        if (result == null) {
            errorMessage = "Our server is under maintenance. Please try again later!";
        } else {
            errorMessage = "Update Failed. Please try again!";
        }
        req.setAttribute("error", errorMessage);
        req.getRequestDispatcher("/WEB-INF/pages/updateDrinks.jsp").forward(req, resp);
    }
}
