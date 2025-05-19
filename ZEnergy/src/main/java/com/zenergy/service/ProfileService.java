package com.zenergy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import com.zenergy.config.DbConfig;
import com.zenergy.model.UserModel;

public class ProfileService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    public ProfileService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public UserModel getUserByUsername(String username) {
        if (isConnectionError) return null;

        String query = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserModel(
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getDate("birthday").toLocalDate(),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("password"),
                    rs.getString("image_path")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateUserProfile(UserModel user) {
        if (isConnectionError) return false;

        String updateSQL = "UPDATE user SET first_name = ?, last_name = ?, email = ?, phone_number = ?, gender = ?, birthday = ?, image_path = ? WHERE username = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(updateSQL)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getNumber());
            stmt.setString(5, user.getGender());
            stmt.setDate(6, java.sql.Date.valueOf(user.getDob()));  
            stmt.setString(7, user.getImageUrl());
            stmt.setString(8, user.getUserName());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
