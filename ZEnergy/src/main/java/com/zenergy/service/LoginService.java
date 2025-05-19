package com.zenergy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zenergy.config.DbConfig;
import com.zenergy.model.UserModel;
import com.zenergy.util.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class LoginService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor initializes the database connection. Sets the connection error
     * flag if the connection fails.
     */
    public LoginService() {
        try {
            dbConn = DbConfig.getDbConnection();
            System.out.println("Database connection initialized.");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Failed to initialize database connection: " + ex.getMessage());
            isConnectionError = true;
        }
    }

    /**
     * Validates the user credentials against the database records.
     *
     * @param customerModel the CustomerModel object containing user credentials
     * @return true if the user credentials are valid, false otherwise; null if a
     *         connection error occurs
     */
    public Boolean loginUser(UserModel userModel) {
        if (isConnectionError) {
            System.out.println("Database connection error. Unable to perform login.");
            return null;
        }

        // Updated query with correct table name "customerInfo"
        String query = "SELECT username, password FROM user WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, userModel.getUserName());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                System.out.println("User found in database: " + userModel.getUserName());
                return validatePassword(result, userModel);
            } else {
                System.out.println("User not found: " + userModel.getUserName());
            }
        } catch (SQLException e) {
            System.out.println("Database error while executing login query: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return false;
    }
    
    /**
     * Fetches full user details from the database based on username.
     *
     * @param username the username to search for
     * @return CustomerModel with user details if found; otherwise null
     */
    public UserModel getUserDetails(String username) {
        if (isConnectionError) {
            System.out.println("Database connection error. Unable to fetch user details.");
            return null;
        }

        String query = "SELECT name, username, email, phoneNumber, address, gender, profilePicture FROM customerInfo WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
            	UserModel customer = new UserModel();
                customer.setFirstName(result.getString("name"));
                customer.setUserName(result.getString("username"));
                customer.setEmail(result.getString("email"));
                customer.setNumber(result.getString("phoneNumber"));         
                customer.setGender(result.getString("gender"));

                return customer;
            } else {
                System.out.println("No user details found for username: " + username);
            }
        } catch (SQLException e) {
            System.out.println("Database error while fetching user details: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Validates the password retrieved from the database.
     *
     * @param result        the ResultSet containing the username and password from
     *                      the database
     * @param userModel the CustomerModel object containing user credentials
     * @return true if the passwords match, false otherwise
     * @throws SQLException if a database access error occurs
     */
    private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
        String dbUsername = result.getString("username");
        String dbPassword = result.getString("password");

        if (dbUsername.equals(userModel.getUserName())) {
        	String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);

        	if (decryptedPassword == null) {
        	    System.err.println("Decryption failed. Decrypted password is null for user: " + dbUsername);
        	    return false;
        	}

        	boolean isPasswordValid = decryptedPassword.equals(userModel.getPassword());


            if (isPasswordValid) {
                System.out.println("Password matched for user: " + userModel.getUserName());
            } else {
                System.out.println("Invalid password attempt for user: " + userModel.getUserName());
            }

            return isPasswordValid;
        }

        return false;
    }
}