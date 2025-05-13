package com.zenergy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zenergy.config.DbConfig;
import com.zenergy.model.DrinkModel;

/**
 * DrinkService handles the addition of new drinks. It manages database
 * interactions for adding a drink product to the database.
 */
public class DrinkService {

    private Connection dbConn;

    /**
     * Constructor initializes the database connection.
     */
    public DrinkService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Adds a new drink to the database.
     *
     * @param drinkModel the drink details to be added
     * @return Boolean indicating the success of the operation
     */
    public Boolean addDrink(DrinkModel drinkModel) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        String insertQuery = "INSERT INTO energy_drink (brand_name, drink_name, price, flavor, calorie, username, image_path) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

            insertStmt.setString(1, drinkModel.getBrandName());
            insertStmt.setString(2, drinkModel.getDrinkName());
            insertStmt.setDouble(3, drinkModel.getPrice());
            insertStmt.setString(4, drinkModel.getFlavor());
            insertStmt.setInt(5, drinkModel.getCalorie());
            insertStmt.setString(6, drinkModel.getUsername());
            insertStmt.setString(7, drinkModel.getImagePath()); 

            return insertStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during drink addition: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

