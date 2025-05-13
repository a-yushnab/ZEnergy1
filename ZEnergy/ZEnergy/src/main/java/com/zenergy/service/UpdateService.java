package com.zenergy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zenergy.config.DbConfig;
import com.zenergy.model.DrinkModel;

/**
 * Service class for updating drink information in the database.
 * 
 * This class provides methods to update drink details and fetch drink IDs
 * from the database. It manages database connections and handles SQL
 * exceptions.
 */
public class UpdateService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor initializes the database connection. Sets the connection error
     * flag if the connection fails.
     */
    public UpdateService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Updates drink information in the database.
     * 
     * @param drink The DrinkModel object containing the updated drink data.
     * @return Boolean indicating the success of the update operation.
     */
    public Boolean updateDrinkInfo(DrinkModel drink) {
        if (isConnectionError) {
            return null;
        }

        int drinkId = drink.getDrinkId();
        if (drinkId == 0) {
            System.out.println("Invalid drink ID: " + drinkId);
            return false;
        }

        String updateSQL = "UPDATE energy_drink SET brand_name = ?, drink_name = ?, price = ?, flavor = ?, calorie = ?, username = ?, image_path = ? WHERE drink_id = ?";

        try (PreparedStatement preparedStatement = dbConn.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, drink.getBrandName());
            preparedStatement.setString(2, drink.getDrinkName());
            preparedStatement.setDouble(3, drink.getPrice());
            preparedStatement.setString(4, drink.getFlavor());
            preparedStatement.setInt(5, drink.getCalorie());
            preparedStatement.setString(6, drink.getUsername());
            preparedStatement.setString(7, drink.getImagePath()); 
            preparedStatement.setInt(8, drinkId); 

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves a drink record by its ID.
     *
     * @param id The drink ID to fetch.
     * @return A DrinkModel object with the drink data, or null if not found or error.
     */
    public DrinkModel getProductById(int id) {
        if (isConnectionError) return null;

        String query = "SELECT * FROM energy_drink WHERE drink_id = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new DrinkModel(
                    result.getInt("drink_id"),
                    result.getString("brand_name"),
                    result.getString("drink_name"),
                    result.getDouble("price"),
                    result.getString("flavor"),
                    result.getInt("calorie"),
                    result.getString("username"),
                    result.getString("image_path") 
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
