package com.zenergy.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.zenergy.model.UserModel;
import com.zenergy.service.ProfileService;
import com.zenergy.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(asyncSupported = true, urlPatterns = { "/userprofile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,    // 2MB
                 maxFileSize = 1024 * 1024 * 10,         // 10MB
                 maxRequestSize = 1024 * 1024 * 50)      // 50MB
public class profileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProfileService profileService;

    public profileController() {
        this.profileService = new ProfileService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = (String) SessionUtil.getAttribute(req, "username");

        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/login");  
            return;
        }

        UserModel user = profileService.getUserByUsername(username);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/pages/userprofile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = (String) SessionUtil.getAttribute(req, "username");
        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            req.setCharacterEncoding("UTF-8");

            // Form fields
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String number = req.getParameter("number");
            String gender = req.getParameter("gender");
            LocalDate dob = LocalDate.parse(req.getParameter("dob"));

            // Image upload
            Part filePart = req.getPart("newImage"); 
            String fileName = null;
            String imageUrl = null;

            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                String uploadPath = getServletContext().getRealPath("/resources/images/user");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                filePart.write(uploadPath + File.separator + fileName);

                imageUrl = fileName; 
            } else {
                imageUrl = req.getParameter("imageUrl"); 
            }

            // Create UserModel and update
            UserModel user = new UserModel(firstName, lastName, username, dob, gender, email, number, null, imageUrl);

            if (profileService.updateUserProfile(user)) {
                resp.sendRedirect(req.getContextPath() + "/userprofile");
            } else {
                req.setAttribute("error", "Failed to update profile.");
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/pages/userprofile.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Something went wrong.");
            req.getRequestDispatcher("/WEB-INF/pages/userprofile.jsp").forward(req, resp);
        }
    }
}
