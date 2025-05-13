package com.zenergy.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zenergy.config.DbConfig;
import com.zenergy.model.UserModel;

/**
 * RegisterService handles the registration of new users.
 * It manages database interactions for user registration.
 */
public class RegisterService {

    private Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public RegisterService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Registers a new user in the database.
     *
     * @param userModel the user details to be registered
     * @return Boolean indicating the success of the operation
     */
    public Boolean addUser(UserModel userModel) {
    	
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        String insertQuery = "INSERT INTO user (first_name,last_name,username,birthday,gender,email,phone_number,password,image_path)"
       
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

            insertStmt.setString(1, userModel.getFirstName());
            insertStmt.setString(2, userModel.getLastName());
            insertStmt.setString(3, userModel.getUserName());
            insertStmt.setDate(4, Date.valueOf(userModel.getDob()));
            insertStmt.setString(5, userModel.getGender());
            insertStmt.setString(6, userModel.getEmail());
            insertStmt.setString(7, userModel.getNumber());
            insertStmt.setString(8, userModel.getPassword());
            insertStmt.setString(9, userModel.getImageUrl());

            return insertStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during user registration: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
