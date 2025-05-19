package com.zenergy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zenergy.config.DbConfig;
import com.zenergy.model.UserModel;

/**
 * Service class for interacting with the database to manage users.
 */
public class DashboardService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor that initializes the database connection.
     */
    
    public DashboardService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            // Log and handle exceptions related to database connection
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Retrieves all users from the database.
     * 
     * @return A list of UserModel objects containing user data. Returns null if
     *         there's a connection error or exception during query execution.
     */
    public List<UserModel> getAllUsersInfo() {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT username, first_name, last_name, birthday, gender, email, phone_number, password, image_path FROM user WHERE username != 'admin'";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            List<UserModel> userList = new ArrayList<>();

            while (result.next()) {
                userList.add(new UserModel(
                    result.getString("username"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getDate("birthday").toLocalDate(),
                    result.getString("gender"),
                    result.getString("email"),
                    result.getString("phone_number"),
                    result.getString("password"), 
                    result.getString("image_path")
                ));
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves specific user information by username.
     * 
     * @param username the username of the user.
     * @return UserModel object containing the user details.
     */
    public UserModel getSpecificUserInfo(String username) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT username, first_name, last_name, birthday, gender, email, phone_number, image_path FROM users WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();
            UserModel user = null;

            if (result.next()) {
                user = new UserModel(
                    result.getString("username"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getDate("birthday").toLocalDate(),
                    result.getString("gender"),
                    result.getString("email"),
                    result.getString("phone_number"),
                    result.getString("password"),
                    result.getString("image_path")
                );
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds a new user to the database.
     * 
     * @param user the UserModel object containing the new user's data.
     * @return true if the user was added successfully, false otherwise.
     */
    public boolean addUser(UserModel user) {
        if (isConnectionError)
            return false;

        String insertQuery = "INSERT INTO users (username, first_name, last_name, birthday, gender, email, phone_number, password, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConn.prepareStatement(insertQuery)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setDate(4, java.sql.Date.valueOf(user.getDob()));
            stmt.setString(5, user.getGender());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getNumber());
            stmt.setString(8, user.getPassword());
            stmt.setString(9, user.getImageUrl());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates a user in the database.
     * 
     * @param user the UserModel object containing the updated user data.
     * @return true if the user was updated successfully, false otherwise.
     */
    public boolean updateUser(UserModel user) {
        if (isConnectionError)
            return false;

        String updateQuery = "UPDATE users SET first_name = ?, last_name = ?, birthday = ?, gender = ?, email = ?, phone_number = ?, password = ?, image_path = ? WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(updateQuery)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setDate(3, java.sql.Date.valueOf(user.getDob()));
            stmt.setString(4, user.getGender());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getNumber());
            stmt.setString(7, user.getPassword());
            stmt.setString(8, user.getImageUrl());
            stmt.setString(9, user.getUserName());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a user by username.
     * 
     * @param username the username of the user to delete.
     * @return true if the user was deleted successfully, false otherwise.
     */
    public boolean deleteUser(String username) {
        if (isConnectionError)
            return false;

        String deleteQuery = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(deleteQuery)) {
            stmt.setString(1, username);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
