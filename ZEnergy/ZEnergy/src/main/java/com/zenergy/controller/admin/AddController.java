package com.zenergy.controller.admin;

import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;

import com.zenergy.model.DrinkModel;
import com.zenergy.service.DrinkService;
import com.zenergy.util.ImageUtil;
import com.zenergy.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/addDrink")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final DrinkService drinkService = new DrinkService();
    private final ImageUtil imageUtil = new ImageUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/drink.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String error = validateDrinkForm(req);
            if (error != null) {
                handleError(req, resp, error);
                return;
            }

            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                handleError(req, resp, "Session expired or not logged in. Please log in again.");
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }

            String username = (String) session.getAttribute("username");

            // Extract drink details
            DrinkModel drinkModel = extractDrinkModel(req, username);

            // Handle image upload
            Part imagePart = req.getPart("image");
            String imagePath = null;

            if (imagePart != null && imagePart.getSize() > 0) {
                // Define image upload directory
                String uploadDir = getServletContext().getRealPath("/uploads");
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs(); // Create the directory if it doesn't exist
                }

                // Get the file name and write the image to the directory
                String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                String filePath = uploadDir + File.separator + fileName;
                imagePart.write(filePath);

                // Set the image path to store in the database
                imagePath = "uploads/" + fileName;

                // Update the drink model with the image path
                drinkModel.setImagePath(imagePath);
            }

            // Add the drink with all details including the image path
            boolean isAdded = drinkService.addDrink(drinkModel);

            if (isAdded) {
                handleSuccess(req, resp, "Drink successfully added!");
            } else {
                handleError(req, resp, "Failed to add drink. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleError(req, resp, "An unexpected error occurred. Please try again.");
        }
    }
    
    private String validateDrinkForm(HttpServletRequest req) {
        if (ValidationUtil.isNullOrEmpty(req.getParameter("brandName")))
            return "Brand name is required.";
        if (ValidationUtil.isNullOrEmpty(req.getParameter("drinkName")))
            return "Drink name is required.";
        if (ValidationUtil.isNullOrEmpty(req.getParameter("price")))
            return "Price is required.";
        if (ValidationUtil.isNullOrEmpty(req.getParameter("flavor")))
            return "Flavor is required.";
        if (ValidationUtil.isNullOrEmpty(req.getParameter("calorie")))
            return "Calorie is required.";

        try {
            Double.parseDouble(req.getParameter("price"));
        } catch (NumberFormatException e) {
            return "Price must be a valid number.";
        }

        try {
            Integer.parseInt(req.getParameter("calorie"));
        } catch (NumberFormatException e) {
            return "Calorie must be a valid integer.";
        }

        try {
            Part image = req.getPart("image");
            if (!ValidationUtil.isValidImageExtension(image)) {
                return "Invalid image format. Only jpg, jpeg, png, gif allowed.";
            }
        } catch (Exception e) {
            return "Error handling image file.";
        }

        return null;
    }

    private DrinkModel extractDrinkModel(HttpServletRequest req, String username) throws Exception {
        String brandName = req.getParameter("brandName");
        String drinkName = req.getParameter("drinkName");
        double price = Double.parseDouble(req.getParameter("price"));
        String flavor = req.getParameter("flavor");
        int calorie = Integer.parseInt(req.getParameter("calorie"));
        Part image = req.getPart("image");
        String imagePath = imageUtil.getImageNameFromPart(image);

        return new DrinkModel(0, brandName, drinkName, price, flavor, calorie, username, imagePath);
    }

    private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
        Part image = req.getPart("image");
        return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "drink");
    }

    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message)
            throws IOException {
        req.getSession().setAttribute("success", message);
        resp.sendRedirect(req.getContextPath() + "/products");
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.setAttribute("brandName", req.getParameter("brandName"));
        req.setAttribute("drinkName", req.getParameter("drinkName"));
        req.setAttribute("price", req.getParameter("price"));
        req.setAttribute("flavor", req.getParameter("flavor"));
        req.setAttribute("calorie", req.getParameter("calorie"));
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}
