package com.zenergy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.zenergy.config.DbConfig;

public class DbConnectionTest {
    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection conn = DbConfig.getDbConnection();
            if (conn != null) {
                System.out.println("✅ Database connected successfully!");
                
                // Test the insert query
                String insertQuery = "INSERT INTO student (first_name, last_name, username, dob, gender, email, number, password, image_path) " +
                                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
                // Prepared statement to prevent SQL injection
                PreparedStatement stmt = conn.prepareStatement(insertQuery);
                
                // Set parameters for the insert query
                stmt.setString(1, "John");
                stmt.setString(2, "Doe");
                stmt.setString(3, "johndoe");
                stmt.setDate(4, java.sql.Date.valueOf("1995-05-20"));
                stmt.setString(5, "male");
                stmt.setString(6, "johndoe@example.com");
                stmt.setString(7, "1234567890");
                stmt.setString(8, "password123");
                stmt.setString(9, "path/to/profile/image");

                // Execute the insert query
                int rowsAffected = stmt.executeUpdate();

                // Check if the insert was successful
                if (rowsAffected > 0) {
                    System.out.println("✅ Inserted a new record successfully!");
                } else {
                    System.out.println("❌ Failed to insert the record.");
                }
                
                // Close the statement
                stmt.close();
            } else {
                System.out.println("❌ Failed to connect to the database.");
            }
            conn.close(); // Close the connection
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ SQL error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
